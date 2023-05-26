package com.senior.naudo.emailsender.service;

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

import org.springframework.stereotype.Service;

import com.senior.naudo.emailsender.exception.MailFailedException;
import com.senior.naudo.emailsender.model.EmailModule;

@Service
public class EmailService {
	
	Properties props = new Properties();
	
	
	   
	public void sendEmail(EmailModule mail) {
		
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    
	    Session session = Session.getDefaultInstance(props,
	    	      new Authenticator() {
	    	           protected PasswordAuthentication getPasswordAuthentication() {
	    	                 return new PasswordAuthentication(mail.getFrom(),
	    	                 mail.getPasswordUser());
	    	           }
	    	      });
	    
	    session.setDebug(true);

	    try {
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(mail.getFrom()));
	      
	      Address[] toUser = InternetAddress.parse(mail.getAddressee());

	      message.setRecipients(Message.RecipientType.TO, toUser);
	      message.setSubject(mail.getSubject());
	      message.setText(mail.getBody());
	      Transport.send(message);

	     } catch (MessagingException e) {
	        throw new MailFailedException(e.getMessage());
	    }
	}
}
