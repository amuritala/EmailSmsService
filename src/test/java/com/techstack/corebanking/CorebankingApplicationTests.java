package com.techstack.corebanking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
		"SMTP_HOST=localhost",
		"SMTP_PORT=587",
		"SMTP_USER=test@example.com",
		"SMTP_PASSWORD=test",
		"SMTP_FROM=test@example.com",
		"SMTP_STARTTLS=true",
		"SMTP_SSL=false"
})
class CorebankingApplicationTests {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Test
	void contextLoads() {
		assertEquals("localhost", mailSender.getHost());
		assertEquals(587, mailSender.getPort());
		assertEquals("test@example.com", mailSender.getUsername());
		assertEquals("test", mailSender.getPassword());
		assertEquals("true", mailSender.getJavaMailProperties().getProperty("mail.smtp.starttls.enable"));
		assertEquals("true", mailSender.getJavaMailProperties().getProperty("mail.smtp.starttls.required"));
		assertEquals("false", mailSender.getJavaMailProperties().getProperty("mail.smtp.ssl.enable"));
		assertEquals("5000", mailSender.getJavaMailProperties().getProperty("mail.smtp.connectiontimeout"));
	}

}
