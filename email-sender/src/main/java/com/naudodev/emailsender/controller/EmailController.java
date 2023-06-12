package com.naudodev.emailsender.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.dto.EmailWithManyAddresseeDTO;
import com.naudodev.emailsender.service.data.EmailDataService;
import com.naudodev.emailsender.service.sender.EmailSenderService;

@Controller()
@RequestMapping("/api/v1/sendmail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

	private EmailSenderService sender;
	private EmailDataService data;

	public EmailController(EmailSenderService service, EmailDataService data) {
		this.sender = service;
		this.data = data;
	}
	
	@PostMapping("")
	public ResponseEntity<String> sendEmailWithDefaultMail(@RequestBody EmailDTO dto) throws IOException {
		sender.sendEmailDefault(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Email sended successfully");
	}
	
	@PostMapping("/many")
	public ResponseEntity<String> sendEmailWithDefaultMail(@RequestBody EmailWithManyAddresseeDTO dto) throws IOException {
		sender.sendEmailDefaultToManyAddressee(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Email sended successfully");
	}

	@GetMapping("/get")
	public ResponseEntity<List<EmailDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(data.getAllMails());
	}
	
	@GetMapping("/get/by/id/{id}")
	public ResponseEntity<EmailDTO> getOne(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getOne(UUID.fromString(id)));
	}

	@GetMapping("/get/by/addressee/{mail}")
	public ResponseEntity<List<EmailDTO>> findAllByAddressee(@PathVariable String mail) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getMailsWhereAddresseeEmail(mail));
	}
	
	@GetMapping("/get/by/owner/{owner}")
	public ResponseEntity<List<EmailDTO>> findAllByOnwer(@PathVariable String owner) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getAllByOwners(owner));
	}
	
	@GetMapping("/get/by/owner/{owner}/and/date/{date1}/{date2}")
	public ResponseEntity<List<EmailDTO>> findAllByOnwer(@PathVariable String owner, @PathVariable String date1, @PathVariable String date2) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getAllByOwnerAndDate(owner, LocalDateTime.parse(date1), LocalDateTime.parse(date2)));
	}
	
	@GetMapping("/get/by/date/{date1}/{date2}")
	public ResponseEntity<List<EmailDTO>> getAllByDateBetween(@PathVariable String date1, @PathVariable String date2) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getMailsBetweenDate(LocalDateTime.parse(date1), LocalDateTime.parse(date2)));
	}
	
	@DeleteMapping("/delete/by/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) {
		data.deleteMailbyId(UUID.fromString(id));
		return ResponseEntity.status(HttpStatus.OK).body("Mails deleted by successfully");
	}	
	
	@DeleteMapping("/delete/by/date/{date1}/{date2}")
	public ResponseEntity<String> deleteAllByDateBetween(@PathVariable String date1, @PathVariable String date2) {
		data.deleteMailsByBetweenDate(LocalDateTime.parse(date1), LocalDateTime.parse(date2));
		return ResponseEntity.status(HttpStatus.OK).body("Mails deleted by successfully");
	}
	
	@DeleteMapping("/delete/by/owner/{owner}")
	public ResponseEntity<String> deleteAllByOwner(@PathVariable String owner) {
		data.deleteMailsbyOwner(owner);
		return ResponseEntity.status(HttpStatus.OK).body("Mails deleted by successfully");
	}
}