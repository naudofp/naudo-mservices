package com.naudodev.emailsender.projection;

import java.time.LocalDateTime;
import java.util.UUID;

public interface EmailProjection {

	UUID getId();
	
	String getSubject();
	
	String getBody();
	
	String getAddressee();
	
	String getOwner();
	
	LocalDateTime getDateSent();
}
