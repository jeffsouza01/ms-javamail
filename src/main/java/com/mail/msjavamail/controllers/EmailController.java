package com.mail.msjavamail.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.msjavamail.dtos.EmailDTO;
import com.mail.msjavamail.models.Email;
import com.mail.msjavamail.repositories.EmailRepository;
import com.mail.msjavamail.services.EmailService;

@RestController()
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@Autowired
	EmailRepository emailrepository;
	
	@PostMapping("/sending-email")
	public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
		Email email = new Email();
		BeanUtils.copyProperties(emailDTO, email);
		emailService.sendEmail(email);
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
	
	
	@GetMapping("emails")
	public Page<Email> findAll(Pageable pageable){
		return emailrepository.findAll(pageable);
		
	}
	
	
	
	
}
