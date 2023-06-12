package com.naudodev.emailsender.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naudodev.emailsender.model.EmailModule;
import com.naudodev.emailsender.projection.EmailProjection;

public interface EmailRepository extends JpaRepository<EmailModule, UUID> {
	
	List<EmailProjection> findAllByAddresseeContainingIgnoreCase(String addressee);
	
	List<EmailProjection> findAllByDateSentBetween(LocalDateTime date1, LocalDateTime date2);
	
	List<EmailProjection> findAllByOwner(String owner);
	
	List<EmailProjection> findAllByOwnerAndDateSentBetween(String owner, LocalDateTime date1, LocalDateTime date2);
	
	void deleteAllByDateSentBetween(LocalDateTime date1, LocalDateTime date2);
	
	void deleteAllByOwner(String owner);
	
}
