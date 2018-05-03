package com.nargilemag.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;



public enum MailSender {
	
	INSTANCE;
	
	
	public void sendEmail(JavaMailSenderImpl mailSender, List<String> emails, String mess) {
		
		for (String email : emails) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			try {
				MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);
				mailMsg.setFrom("ittalents.nargilemag@gmail.com");
				mailMsg.setTo(email);
				mailMsg.setSubject("Information mail");
				mailMsg.setText(mess, true);
				mailSender.send(mimeMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
}
}
