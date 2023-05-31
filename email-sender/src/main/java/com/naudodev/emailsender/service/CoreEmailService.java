package com.naudodev.emailsender.service;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;

import org.springframework.core.io.Resource;

import com.naudodev.emailsender.exception.MailFailedException;

public class CoreEmailService {
	
	public static Session initialzrSession(Properties props, String user, String password) {
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
	
	public static Multipart addSignature(Multipart multipart, Resource resource) throws IOException {

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
}
