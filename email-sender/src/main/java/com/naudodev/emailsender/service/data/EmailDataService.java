package com.naudodev.emailsender.service.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.naudodev.emailsender.dto.EmailDTO;

public interface EmailDataService {

	EmailDTO save(EmailDTO dto);
	
	EmailDTO getOne(UUID id); 
	
	List<EmailDTO> getAllMails();
	
	List<EmailDTO> getMailsWhereAddresseeEmail(String email);
	
	List<EmailDTO> getMailsBetweenDate(LocalDateTime date1, LocalDateTime date2);
	
	List<EmailDTO> getAllByOwners(String owner);
	
	List<EmailDTO> getAllByOwnerAndDate(String owner, LocalDateTime date1, LocalDateTime date2);

	void deleteMailsbyOwner(String owner);

	void deleteMailsByBetweenDate(LocalDateTime date1, LocalDateTime date2);

	void deleteMailbyId(UUID id);
}
