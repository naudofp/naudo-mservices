package com.naudodev.emailsender.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.naudodev.emailsender.model.EmailModule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class EmailWithManyAddresseeDTO {

		private UUID id;
		@Email
		private List<String> addressees;
		@NotBlank
		private String subject;
		@NotEmpty
		private String body;
		@Email
		private String owner;
		
		private LocalDateTime dateSent;
		
		public EmailWithManyAddresseeDTO(UUID id, @NotBlank String subject, @NotEmpty String body, String owner, LocalDateTime dateSent) {
			this.id = id;
			this.subject = subject;
			this.body = body;
			this.owner = owner;
			this.dateSent = dateSent;
			this.addressees = new ArrayList<>();
		}
		
		public EmailWithManyAddresseeDTO() {}

		public EmailWithManyAddresseeDTO(EmailModule entity, List<String> addressee){
			this(entity.getId(), entity.getSubject(), entity.getBody(), entity.getOwner(), entity.getDateSent());
			this.addressees = addressee;
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
		public List<String> getAddressees() {
			return addressees;
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
		public void setOwner(String owner) {
			this.owner = owner;
		}
		public void setAddressees(List<String> addressees) {
			this.addressees = addressees;
		}
		public LocalDateTime getDateSent() {
			return dateSent;
		}
		public void setDateSent(LocalDateTime dateSent) {
			this.dateSent = dateSent;
		}
}
