package com.naudodev.emailsender.repository;

import java.util.List;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naudodev.emailsender.model.EmailModule;

public interface EmailRepository extends JpaRepository<EmailModule, UUID> {
	
	List<EmailModule> findAllByAddresseeContainingIgnoreCase(String addressee);
	
	List<EmailModule> findAllByFromContainingIgnoreCase(String from);
}
