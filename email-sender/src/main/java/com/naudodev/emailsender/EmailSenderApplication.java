package com.naudodev.emailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Bem vindo ao código do projeto email-sender.
 * Toda a documentação será feita em PT-BR.
 * Em https://github.com/naudofp/naudo-mservices/tree/emailsender
 * em documentation.pdf será feito uma tradução automática para ENG-USA
 * 
 * V1.0-SNAPSHOT ainda está aberta a alguns bugs e erros, porém será melhorada ao passar do tempo.
 * Essa versão está limitada apenas a envio de mensagens com o endereço @gmail.
*/

@SpringBootApplication
public class EmailSenderApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EmailSenderApplication.class, args);
	}
}
