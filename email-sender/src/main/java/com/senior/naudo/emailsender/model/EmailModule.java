package com.senior.naudo.emailsender.model;

import java.util.UUID;

import org.hibernate.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_mail")
public class EmailModule {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_mail")
	private UUID id;
	
	@NotNull
	@Column(name = "subject_mail", length = Length.DEFAULT, nullable = false)
	private String subject;
	
	@Column(name = "body_mail", length = Length.LONG32, nullable = false)
	private String body;
	
	@NotBlank
	@Email
	@Column(name = "addressee_mail", length = Length.DEFAULT, nullable = false)
	private String addressee;
	
	public EmailModule(UUID id, String subject, String body, String addressee) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.addressee = addressee;
	}
	
	public EmailModule() {}
	
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
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
}
