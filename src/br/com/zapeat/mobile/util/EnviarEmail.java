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
	        
	        props.put("mail.smtp.auth", "true"); // ativa autenticacao
			props.put("mail.smtp.user", from); // usuario ou seja, a conta que esta
												// enviando o email (tem que ser do
												// GMAIL)
			props.put("mail.debug", "false");
			props.put("mail.smtp.port", Constantes.PORTAL_GMAIL);
			props.put("mail.smtp.socketFactory.port", Constantes.PORTAL_GMAIL); 
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
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