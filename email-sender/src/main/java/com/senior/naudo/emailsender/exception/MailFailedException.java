package com.senior.naudo.emailsender.exception;

public class MailFailedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MailFailedException(String message) {
		super(message);
	}
	
}
