package com.naudodev.emailsender.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naudodev.emailsender.dto.EmailDTO;
import com.naudodev.emailsender.service.EmailSenderService;

@Controller()
@RequestMapping("/api/v1/sendmail")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmailController {

	private EmailSenderService service;

	public EmailController(EmailSenderService service) {
		this.service = service;
	}

//	This method will send e-mail with the sender set to 
//	"noreplay.naudo@gmail.com" by default. Learn more -> com.senior.naudo
	
	@GetMapping("/default")
	public ResponseEntity<String> sendEmailWithDefaultMail(@RequestBody EmailDTO dto) throws IOException {
		service.sendEmailDefault(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}
	
	@GetMapping("")
	public ResponseEntity<String> sendEmailwithOtherMailAuthorized(@RequestBody EmailDTO dto) {
		service.sendEmailWithOtherUserSender(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}
}