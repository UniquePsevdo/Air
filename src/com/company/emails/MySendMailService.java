package com.company.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("MySendMailService")
public class MySendMailService {
	
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage alertMailMessage;

	public void sendEmail(String to, String from, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	
	public void sendAlertEmail(String alert){
		SimpleMailMessage message = new SimpleMailMessage(alertMailMessage);
		message.setText(alert);
		mailSender.send(message);
	}
}
