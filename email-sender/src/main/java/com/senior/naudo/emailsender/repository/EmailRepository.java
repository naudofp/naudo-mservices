package com.senior.naudo.emailsender.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senior.naudo.emailsender.model.EmailModule;

public interface EmailRepository extends JpaRepository<EmailModule, UUID> {}
