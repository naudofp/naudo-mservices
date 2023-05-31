package com.naudodev.emailsender.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.service.EmailDataService;
import com.naudodev.emailsender.service.EmailSenderService;

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
	public ResponseEntity<String> sendEmailwithOtherMailAuthorized(@RequestBody EmailDTO dto) {
		sender.sendEmailWithOtherUserSender(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}

	@PostMapping("/default")
	public ResponseEntity<String> sendEmailWithDefaultMail(@RequestBody EmailDTO dto) throws IOException {
		sender.sendEmailDefault(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}

	@GetMapping("/get")
	public ResponseEntity<List<EmailDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(data.getAllMails());
	}
	
	@GetMapping("/get/by/addressee/{mail}")
	public ResponseEntity<List<EmailDTO>> findAllByAddressee(@PathVariable String mail) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getMailsWhereAddresseeContainsEmail(mail));
	}
	
	@GetMapping("/get/by/from/{mail}")
	public ResponseEntity<List<EmailDTO>> findAllByFrom(@PathVariable String mail) {
		return ResponseEntity.status(HttpStatus.OK).body(data.getMailsWhereFromContainsEmail(mail));
	}
	
}