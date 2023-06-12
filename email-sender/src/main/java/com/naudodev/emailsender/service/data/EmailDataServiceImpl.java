package com.naudodev.emailsender.service.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.exception.EmailNotFoundException;
import com.naudodev.emailsender.model.EmailModule;
import com.naudodev.emailsender.repository.EmailRepository;

@Service
public class EmailDataServiceImpl implements EmailDataService {

	private EmailRepository repository;
	
	public EmailDataServiceImpl(EmailRepository repository) {
		this.repository = repository;
	}

	public EmailDTO save(EmailDTO dto) {
		EmailModule entity = new EmailModule(
						UUID.randomUUID(),
						dto.getSubject(),
						dto.getBody(),
						dto.getAddressee(),
						dto.getOwner(),
						LocalDateTime.now()
				);

		EmailModule entitySaved = repository.save(entity);
		return new EmailDTO(entitySaved);
	}

	public EmailDTO getOne(UUID id) {
		return new EmailDTO(repository.findById(id).orElseThrow(() -> new EmailNotFoundException("Email with id " + id + " was not found")));
	}

	public List<EmailDTO> getAllMails(){
		return EmailDTO.parseListDto(repository.findAll());
	}

	public List<EmailDTO> getMailsBetweenDate(LocalDateTime date1, LocalDateTime date2) {
		return EmailDTO.parseProjectionListDto(repository.findAllByDateSentBetween(date1, date2));
	}
	
	public List<EmailDTO> getAllByOwners(String owner){
		return EmailDTO.parseProjectionListDto(repository.findAllByOwner(owner));
	}

	public List<EmailDTO> getMailsWhereAddresseeEmail(String email){
		return EmailDTO.parseProjectionListDto(repository.findAllByAddresseeContainingIgnoreCase(email));
	}
	
	public List<EmailDTO> getAllByOwnerAndDate(String owner, LocalDateTime date1, LocalDateTime date2) {
		return EmailDTO.parseProjectionListDto(repository.findAllByOwnerAndDateSentBetween(owner, date1, date2));
	}

	public void deleteMailsbyOwner(String owner) {
		repository.deleteAll(EmailDTO.parseProjectionListModule(repository.findAllByOwner(owner)));
	}

	public void deleteMailsByBetweenDate(LocalDateTime date1, LocalDateTime date2) {
		repository.deleteAll(EmailDTO.parseProjectionListModule(repository.findAllByDateSentBetween(date1, date2)));
	}

	public void deleteMailbyId(UUID id) {
		repository.deleteById(id);
	}

}
