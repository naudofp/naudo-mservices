package com.naudodev.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Properties;

import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Test;

import com.naudodev.emailsender.service.CoreEmailService;

public class EmailSenderTest {
	

	@Test
	void sendMailDefault() {
		assertThrows(AddressException.class, () -> CoreEmailService.initialzrSession(new Properties(), "felipenaudof@gmail.com", "lllsenhatest"));
	}
}
