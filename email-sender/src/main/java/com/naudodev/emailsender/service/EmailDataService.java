package com.naudodev.emailsender.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.model.EmailModule;
import com.naudodev.emailsender.repository.EmailRepository;

@Service
public class EmailDataService {

	EmailRepository repository;
	
	public EmailDataService(EmailRepository repository) {
		this.repository = repository;
	}

	public EmailDTO save(EmailDTO dto) {
		EmailModule entity = new EmailModule(
						UUID.randomUUID(),
						dto.getSubject(),
						dto.getBody(),
						dto.getAddressee(),
						dto.getFrom());
		
		EmailModule entitySaved = repository.save(entity);
		return new EmailDTO(entitySaved);
	}
}
