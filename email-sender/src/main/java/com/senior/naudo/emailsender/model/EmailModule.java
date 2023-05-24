package com.senior.naudo.emailsender.model;

public class EmailModule {

	private String addressee;
	private String subject;
	private String body;
	
	public EmailModule(String addressee, String subject, String body) {
		this.addressee = addressee;
		this.subject = subject;
		this.body = body;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
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
}
