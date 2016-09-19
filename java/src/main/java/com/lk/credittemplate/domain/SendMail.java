package com.lk.credittemplate.domain;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
public class SendMail {
	

	
	 public void sendHtmlEmail(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message) throws AddressException,
	            MessagingException {
	 
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(message, "text/html");
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 public void sendHtmlEmailcc(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message,String[] cclist) throws AddressException,
	            MessagingException {
	 
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        InternetAddress[]  toccAddresses = {new InternetAddress(cclist[0]),new InternetAddress(cclist[1]),new InternetAddress(cclist[2])};
	        /*int count=0;
	        if(cclist != null){
		        for (int i=0;i<cclist.length;i++){
		        	if(cclist[i] != null || StringUtils.isNotEmpty(cclist[i])){
		        		count++;
		        	}
		        }
	        }*/
	        // InternetAddress address=new InternetAddress();
	       /* for (int i=0;i<cclist.length;i++){
	        	if(cclist[i] != null || StringUtils.isNotEmpty(cclist[i])){
	        		address.setAddress(cclist[i]);
	        	}
	        	toccAddresses[i]=address;
	        }*/
//	        msg.setRecipients(Message.RecipientType.CC, toccAddresses);
	        
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setRecipients(Message.RecipientType.CC, toccAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(message, "text/html");
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }

}
