package com.d2c.msg.service;

public interface EmailService {

	boolean send(String to, String subject, String content);

}
