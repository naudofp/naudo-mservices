package com.senior.naudo.emailsender.service;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.senior.naudo.emailsender.exception.MailFailedException;
import com.senior.naudo.emailsender.model.EmailModule;

@Service
public class EmailSenderService {

	Properties props;
	@Value("${naudo.config.username}")
	String user;
	@Value("${naudo.config.password}")
	String password;
	@Value("classpath:templates/signature.html")
	Resource resource;
	
	public EmailSenderService() {
		this.props = new Properties();
	}

	public void sendEmail(EmailModule mail) throws IOException{
		Session session = initialzrSession();

		try {
			MimeBodyPart partText = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			partText.setContent(mail.getBody() + "<br><br>", "text/html");
			multipart.addBodyPart(partText, 0);
			multipart = addSignature(multipart);
			
			MimeMessage message = new MimeMessage(session);
			Address[] toUser = InternetAddress.parse(mail.getAddressee());
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(mail.getSubject());
			message.setContent(multipart);
			
			Transport.send(message);

		} catch (MessagingException e) {
			throw new MailFailedException(e.getMessage());
		}
	}
	
	public Multipart addSignature(Multipart multipart) throws IOException {
		MimeBodyPart part = new MimeBodyPart();
		
		try {
			DataHandler source = new DataHandler(new FileDataSource(resource.getFile()));
			part.setDataHandler(source);
			multipart.addBodyPart(part);
		} catch (MessagingException e) {
			throw new MailFailedException("Failed while implemented signature");
		}
		
		return multipart;
	}
	
	public Session initialzrSession() {
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
		return session;
	}
}
