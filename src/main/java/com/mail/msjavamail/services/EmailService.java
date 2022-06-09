package com.mail.msjavamail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mail.msjavamail.enums.StatusEmail;
import com.mail.msjavamail.models.Email;
import com.mail.msjavamail.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;

	public Email sendEmail(Email email) {
		email.setSendDateEmail(LocalDateTime.now());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText());
			
			emailSender.send(message);
			
			email.setStatusEmail(StatusEmail.SENT);
		} catch(MailException e) {
			email.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return emailRepository.save(email);
		}
		
	}
	
	
}
