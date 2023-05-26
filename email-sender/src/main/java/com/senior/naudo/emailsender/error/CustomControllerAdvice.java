package com.senior.naudo.emailsender.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senior.naudo.emailsender.exception.MailFailedException;

public class CustomControllerAdvice {

	@ExceptionHandler(MailFailedException.class)
	public ResponseEntity<ErrorResponse> MailFailedException(Exception exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	}
}
