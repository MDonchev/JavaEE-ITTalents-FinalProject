package com.nargilemag.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

@Controller
public class MailSender {

	@Autowired
	private static JavaMailSenderImpl mailSender;
	
	public static void sendEmail(List<String> emails) {
		
		for (String email : emails) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			try {
				MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true);
				mailMsg.setFrom("ittalents.nargilemag@gmail.com");
				mailMsg.setTo(email);
				mailMsg.setSubject("Test mail");
				mailMsg.setText("Your favourite product has been changed", true);
				mailSender.send(mimeMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}
