package com.sunbin.springboot.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailService {

	private final Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.from.addr")
	private String from;

	public boolean sendSimpleMail(String from, String to, String subject, String content) {
		if (StringUtils.isEmpty(from)) {
			from = this.from;
		}
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(content);
		try {
			mailSender.send(mail);
			logger.info("simple mail send success.");
			return true;
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("simple mail send error.");
			return false;
		}
	}
}
