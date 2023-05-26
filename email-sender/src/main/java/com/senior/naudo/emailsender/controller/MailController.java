package com.senior.naudo.emailsender.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.senior.naudo.emailsender.model.EmailModule;
import com.senior.naudo.emailsender.service.EmailService;

@Controller
public class MailController {

	private EmailService service;

	public MailController(EmailService service) {
		this.service = service;
	}

	@GetMapping("/green")
	public ResponseEntity<String> teste(@RequestBody EmailModule dto) {
		System.out.println(dto.getAddressee());
		service.sendEmail(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Email sended successfully");
	}
}
