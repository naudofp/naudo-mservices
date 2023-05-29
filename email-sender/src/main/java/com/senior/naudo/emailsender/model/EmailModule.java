package com.senior.naudo.emailsender.model;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailModule {

	private UUID id;
	@NotNull
	private String subject;
	private String body;
	@NotBlank
	@Email
	private String addressee;
	
	public EmailModule(UUID id, String subject, String from, String body) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.addressee = from;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String from) {
		this.addressee = from;
	}
}
