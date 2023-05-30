package com.senior.naudo.emailsender.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.senior.naudo.emailsender.dto.EmailDTO;
import com.senior.naudo.emailsender.model.EmailModule;
import com.senior.naudo.emailsender.repository.EmailRepository;

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
						dto.getToUser());
		
		EmailModule entitySaved = repository.save(entity);
		return new EmailDTO(entitySaved);
	}
}
