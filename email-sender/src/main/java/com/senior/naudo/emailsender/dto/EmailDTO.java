package com.senior.naudo.emailsender.dto;

import java.util.UUID;

import com.senior.naudo.emailsender.model.EmailModule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailDTO {

	private UUID id;
	@NotBlank
	private String toUser;
	@NotNull
	private String subject;
	@NotEmpty
	private String body;
	
	public EmailDTO() {}
	
	public EmailDTO(UUID id, String toUser, String subject, String body) {
		this.id = id;
		this.toUser = toUser;
		this.subject = subject;
		this.body = body;
	}
	
	public EmailDTO(EmailModule entity){
		this(entity.getId(), entity.getSubject(), entity.getBody(), entity.getAddressee());
	}

	public EmailModule dtoToEntity(EmailDTO dto) {
		return new EmailModule(this.id, this.subject, this.body, this.toUser);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
