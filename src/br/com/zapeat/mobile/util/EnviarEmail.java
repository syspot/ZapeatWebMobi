package br.com.zapeat.mobile.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class EnviarEmail {

	private EnviarEmail() { 
	}

	public static void enviar(String from, String to, String subject, String msg) throws UnsupportedEncodingException, MessagingException {
		
		 Properties props = System.getProperties();

	        props.put("mail.smtp.host", Constantes.SMTP_GMAIL);

	        props.put("mail.smtp.auth", "true");
	        
	        props.put("mail.smtp.starttls.enable","true"); 

	        Authenticator auth = new Authenticator() {

	            @Override
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(Constantes.ZAPEAT_GMAIL, Constantes.ZAPEAT_SENHA_GMAIL);
	            }
	        };

	        Session session = Session.getInstance(props, auth);
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(Constantes.ZAPEAT_GMAIL, "Zapeat"));
	        message.addRecipient(Message.RecipientType.TO,
	                new InternetAddress(to, ""));
	        message.setSubject(subject);
	        message.setContent(msg, "text/html");
	        

	        Transport.send(message);		

	}
}