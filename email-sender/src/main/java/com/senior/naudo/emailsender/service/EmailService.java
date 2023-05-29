package com.senior.naudo.emailsender.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.senior.naudo.emailsender.exception.MailFailedException;
import com.senior.naudo.emailsender.model.EmailModule;

@Service
public class EmailService {

	Properties props = new Properties();

	@Value("${naudo.config.username}")
	String user;
	@Value("${naudo.config.password}")
	String password;

	@Value("classpath:templates/signature.html")
	Resource resource;

	public void sendEmail(EmailModule mail) throws IOException{

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			Address[] toUser = InternetAddress.parse(mail.getAddressee());
			String body = mail.getBody();
			byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(mail.getSubject());
			message.setContent(body + "\n\n" + new String(bytes, StandardCharsets.UTF_8), "text/html; charset=utf-8");

			
			Transport.send(message);

		} catch (MessagingException e) {
			throw new MailFailedException(e.getMessage());
		}
	}
}
