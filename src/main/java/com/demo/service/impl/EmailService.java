package com.demo.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.mail.SimpleMailMessage;

import javax.security.auth.Subject;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;



@RequiredArgsConstructor
@Service
public class EmailService {

	@Autowired
	 private  JavaMailSender javaMailSender;

	    public void sendVerificationEmail(String userEmail, String otp,
	    		String subject, String text) throws MessagingException {
	    try {
	    	
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "Utf-8");
           
            mimeMessageHelper.setSubject(subject);
           
            mimeMessageHelper.setText(text);
           mimeMessageHelper.setTo(userEmail); // Replace with your email

            javaMailSender.send(mimeMessage);
            
        } catch(MailException e) {
        	System.out.println("errorrr"+e);
            throw new MailSendException("failed to send email");
            	
            }
        }
	        
	}
	

