package com.naudodev.emailsender.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Length;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_mail")
public class EmailModule {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_mail")
	private UUID id;
//	Titulo/Assunto da mensagem.
	@NotNull
	@Column(name = "subject_mail", length = Length.DEFAULT, nullable = false)
	private String subject;
//	Corpo/Conteúdo da mensagem.
	@Column(name = "body_mail", length = Length.LONG32, nullable = false)
	private String body;
//	Neste campo é informado o destinatário. Pode ser enviado para qualquer endereço
//	de e-mail.
	@NotBlank
	@Email
	@Column(name = "addressee_mail", length = Length.DEFAULT, nullable = false)
	private String addressee;
	@NotBlank
	@Column(name = "owner_mail", length = Length.LONG, nullable = false)
	private String owner;
	@Column(name = "datesent_mail")
	private LocalDateTime dateSent;

	public EmailModule(UUID id, @NotNull String subject, String body, @NotBlank @Email String addressee, @NotBlank String owner, LocalDateTime dateSent) {
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.addressee = addressee;
		this.owner = owner;
		this.dateSent = dateSent;
	}

	public EmailModule(UUID id, @NotNull String subject, String body, @NotBlank @Email String addressee, @NotBlank String owner) {
		this(id,subject, body, addressee, owner, LocalDateTime.now());
	}

	protected EmailModule() {}

	@Override
	public int hashCode() {
		return Objects.hash(addressee, id, owner, subject);
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
		return Objects.equals(addressee, other.addressee) && Objects.equals(id, other.id)
				&& Objects.equals(owner, other.owner) && Objects.equals(subject, other.subject);
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getDateSent() {
		return dateSent;
	}

	public void setDateSent(LocalDateTime dateSent) {
		this.dateSent = dateSent;
	}
}
