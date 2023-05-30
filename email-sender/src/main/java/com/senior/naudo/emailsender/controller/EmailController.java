package com.senior.naudo.emailsender.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.senior.naudo.emailsender.model.EmailModule;
import com.senior.naudo.emailsender.service.EmailSenderService;

@Controller
public class EmailController {

	private EmailSenderService service;

	public EmailController(EmailSenderService service) {
		this.service = service;
	}

	@GetMapping("/green")
	public ResponseEntity<String> teste(@RequestBody EmailModule dto) throws IOException {
		service.sendEmail(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}
}