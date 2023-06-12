package com.naudodev.emailsender.service.sender;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.dto.EmailWithManyAddresseeDTO;
import com.naudodev.emailsender.exception.MailFailedException;
import com.naudodev.emailsender.service.core.CoreEmailServiceImpl;
import com.naudodev.emailsender.service.data.EmailDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private EmailDataService data;
	private Properties props;
	@Value("${naudo.config.username}")
	private String user;
	@Value("${naudo.config.passcode}")
	private String passcode;
	@Value("classpath:templates/signature.html")
	private Resource resource;
	
	public EmailSenderServiceImpl(EmailDataService data) {
		this.data = data;
		this.props = new Properties();
	}

	public void sendEmailDefault(EmailDTO mail) throws IOException{
		Session session = CoreEmailServiceImpl.initialzrSession(props, user, passcode);

		try {
			MimeBodyPart partText = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			partText.setContent(mail.getBody() + "<br><br>", "text/html");
			multipart.addBodyPart(partText, 0);
			multipart = CoreEmailServiceImpl.addSignature(multipart, resource);

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

	@Override
	public void sendEmailDefaultToManyAddressee(EmailWithManyAddresseeDTO dto) throws IOException {
		dto.getAddressees().forEach(x -> {
			try {
				sendEmailDefault(new EmailDTO(dto.getId(), dto.getSubject(), dto.getBody(), x, dto.getOwner()));
			} catch (IOException e) {
				throw new MailFailedException(e.getMessage());
			}
		});
	}

}
