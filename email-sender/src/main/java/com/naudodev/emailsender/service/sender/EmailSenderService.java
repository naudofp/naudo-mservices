package com.naudodev.emailsender.service.sender;

import java.io.IOException;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.dto.EmailWithManyAddresseeDTO;

public interface EmailSenderService {
	
	public void sendEmailDefaultToManyAddressee(EmailWithManyAddresseeDTO dto) throws IOException;

	public void sendEmailDefault(EmailDTO mail) throws IOException; 
	
}
