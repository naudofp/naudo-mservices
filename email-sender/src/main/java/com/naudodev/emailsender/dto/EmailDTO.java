package com.naudodev.emailsender.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.naudodev.emailsender.model.EmailModule;

import com.naudodev.emailsender.projection.EmailProjection;
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
	@NotBlank
	private String owner;
	private LocalDateTime dateSent;

	public EmailDTO() {}
	
	public EmailDTO(UUID id, String subject, String body,  String addressee, String owner, LocalDateTime dateSent) {
		this.id = id;
		this.addressee = addressee;
		this.subject = subject;
		this.body = body;
		this.owner = owner;
		this.dateSent = dateSent;
	}

	public EmailDTO(UUID id, String subject, String body,  String addressee, String owner) {
		this(id, subject, body, addressee, owner, LocalDateTime.now());
	}

	public EmailDTO(EmailModule entity){
		this(entity.getId(), entity.getSubject(),entity.getBody(), entity.getAddressee(), entity.getOwner(), entity.getDateSent());
	}

	public EmailDTO(EmailProjection mail){
		this(mail.getId(), mail.getSubject(),mail.getBody(), mail.getAddressee(), mail.getOwner(), mail.getDateSent());
	}

	public EmailModule dtoToEntity() {
		return new EmailModule(this.id, this.subject, this.body, this.addressee, this.owner, this.dateSent);
	}
	
	public static List<EmailDTO> parseProjectionListDto(List<EmailProjection> emails){
		return emails.stream().map(EmailDTO::new).collect(Collectors.toList());
	}
	
	public static List<EmailModule> parseProjectionListModule(List<EmailProjection> emails){
		return emails.stream().map(x -> new EmailModule(x.getId(), x.getSubject(), x.getBody(), x.getAddressee(), x.getOwner())).collect(Collectors.toList());
	}

	public static List<EmailDTO> parseListDto(List<EmailModule> emails){
		return emails.stream().map(EmailDTO::new).collect(Collectors.toList());
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
	public String getOwner() {
		return owner;
	}
	public LocalDateTime getDateSent() {
		return dateSent;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setDateSent(LocalDateTime dateSent) {
		this.dateSent = dateSent;
	}
}
