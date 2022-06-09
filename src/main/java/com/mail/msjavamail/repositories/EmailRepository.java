package com.mail.msjavamail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mail.msjavamail.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}
