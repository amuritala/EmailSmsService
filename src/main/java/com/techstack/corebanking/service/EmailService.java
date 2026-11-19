package com.techstack.corebanking.service;



import com.techstack.corebanking.dto.EmailRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;


@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final String fromEmail;
    public EmailService(JavaMailSender mailSender, @Value("${spring.mail.from}") String fromEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
    }

    public String sendPlainText(EmailRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(request.getRecipient());
        message.setSubject(request.getSubject());
        message.setText(request.getMsgBody());
        mailSender.send(message);
        return "Mail Sent Successfully";
    }

    public String sendHtml(EmailRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(request.getRecipient());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMsgBody(), true);
        mailSender.send(message);
        return "Mail Sent Successfully";
    }


    public void sendEmail(EmailRequest request, MultipartFile attachment) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        // 'true' enables multipart mode for attachments
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(request.getRecipient());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMsgBody());

        if (attachment != null && !attachment.isEmpty()) {
            helper.addAttachment(attachment.getOriginalFilename(), attachment);
        }

        mailSender.send(message);
    }

}
