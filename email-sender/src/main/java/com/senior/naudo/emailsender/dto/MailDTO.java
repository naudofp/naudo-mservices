package com.senior.naudo.emailsender.dto;

import java.util.UUID;

public class MailDTO {

	private UUID id;
	private String addressee;
	private String subject;
	private String body;
	private String from;
	private String passwordUser;
	
	public MailDTO() {}
	
	public MailDTO(UUID id, String addressee, String subject, String body, String from, String passwordUser) {
		this.id = id;
		this.addressee = addressee;
		this.subject = subject;
		this.body = body;
		this.from = from;
		this.passwordUser = passwordUser;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	
}
