package com.naudodev.emailsender.model;

import java.util.Objects;
import java.util.UUID;

import org.hibernate.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	@Email
	@Column(name = "from_mail", length = Length.DEFAULT, nullable = false)
	private String from;
	@Transient
	private String wordAccess;
	
	public EmailModule(UUID id, @NotNull String subject, String body, @NotBlank @Email String addressee, @Email String from, String wordAccess) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.addressee = addressee;
		this.from = from;
		this.wordAccess = wordAccess;
	}
	
	public EmailModule(UUID id, @NotNull String subject, String body, @NotBlank @Email String addressee, @Email String from) {
		this(id, subject, body, addressee, from, null);
	}
	
	protected EmailModule() {}

	@Override
	public int hashCode() {
		return Objects.hash(addressee, body, from, id, subject, wordAccess);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailModule other = (EmailModule) obj;
		return Objects.equals(addressee, other.addressee) && Objects.equals(body, other.body)
				&& Objects.equals(from, other.from) && Objects.equals(id, other.id)
				&& Objects.equals(subject, other.subject) && Objects.equals(wordAccess, other.wordAccess);
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
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
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getWordAccess() {
		return wordAccess;
	}
	public void setWordAccess(String wordAccess) {
		this.wordAccess = wordAccess;
	}
}
