package com.naudodev.emailsender.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.exception.MailFailedException;

@Service
public class EmailSenderService {

	private EmailDataService data;
	private Properties props;
	@Value("${naudo.config.username}")
	private String user;
	@Value("${naudo.config.passcode}")
	private String passcode;
	@Value("classpath:templates/signature.html")
	private Resource resource;
	
	public EmailSenderService(EmailDataService data) {
		this.data = data;
		this.props = new Properties();
	}

	public void sendEmailDefault(EmailDTO mail) throws IOException{
		Session session = CoreEmailService.initialzrSession(props, user, passcode);
		mail.setFrom(user);

		try {
			MimeBodyPart partText = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			partText.setContent(mail.getBody() + "<br><br>", "text/html");
			multipart.addBodyPart(partText, 0);
			multipart = CoreEmailService.addSignature(multipart, resource);
			
			MimeMessage message = new MimeMessage(session);
			Address[] toUser = InternetAddress.parse(mail.getAddressee());
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(mail.getSubject());
			message.setContent(multipart);
			
			Transport.send(message);
			data.save(mail);
		} catch (MessagingException e) {
			throw new MailFailedException(e.getMessage());
		}
	}
	
	public void sendEmailWithOtherUserSender(EmailDTO mail) {
		Session session = CoreEmailService.initialzrSession(props, mail.getFrom(), mail.getWordAccess());
		
		try {
			MimeBodyPart partText = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			partText.setContent(mail.getBody() + "<br><br>", "text/html");
			multipart.addBodyPart(partText, 0);
			
			MimeMessage message = new MimeMessage(session);
			Address[] toUser = InternetAddress.parse(mail.getAddressee());
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(mail.getSubject());
			message.setContent(multipart);
			
			Transport.send(message);
			data.save(mail);
		} catch (MessagingException e) {
			//TODO IMPLEMENTS
			throw new MailFailedException("Mail unauthorized, Learn more at ");
			
		}
	}
	
	
}
