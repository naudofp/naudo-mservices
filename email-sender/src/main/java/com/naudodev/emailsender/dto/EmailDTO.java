package com.naudodev.emailsender.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.naudodev.emailsender.model.EmailModule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmailDTO {

	private UUID id;
	@NotBlank
	@Email
	private String addressee;
	@NotNull
	private String subject;
	@NotEmpty
	private String body;
	@Email
	private String from;
	private String wordAccess;
	
	public EmailDTO() {}
	
	public EmailDTO(UUID id, String addressee, String subject, String body, String from, String wordAccess) {
		this.id = id;
		this.addressee = addressee;
		this.subject = subject;
		this.body = body;
		this.from = from;
		this.wordAccess = wordAccess;
	}
	
	public EmailDTO(EmailModule entity){
		this(entity.getId(), entity.getSubject(), entity.getBody(), entity.getAddressee(), entity.getFrom(), entity.getWordAccess());
	}

	public EmailModule dtoToEntity(EmailDTO dto) {
		return new EmailModule(this.id, this.subject, this.body, this.addressee, this.from, this.wordAccess);
	}
	
	public static List<EmailDTO> parseListDto(List<EmailModule> emails){
		List<EmailDTO> dtos = new ArrayList<>();
		emails.forEach(mail -> dtos.add(new EmailDTO(mail)));
		return dtos;
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
	public void setAddressee(String toUser) {
		this.addressee = toUser;
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
	public String getWordAccess() {
		return wordAccess;
	}
	public void setWordAccess(String wordAccess) {
		this.wordAccess = wordAccess;
	}
}
