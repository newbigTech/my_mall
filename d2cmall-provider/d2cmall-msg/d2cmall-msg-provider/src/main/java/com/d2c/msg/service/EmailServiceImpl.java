package com.d2c.msg.service;

import org.springframework.stereotype.Service;

import com.d2c.msg.third.email.EmailClient;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Override
	public boolean send(String to, String subject, String content) {
		return EmailClient.sendOut(to, subject, content);
	}

}
