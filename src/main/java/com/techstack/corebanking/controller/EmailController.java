package com.techstack.corebanking.controller;


import com.techstack.corebanking.dto.EmailRequest;
import com.techstack.corebanking.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;


    // Send simple email
    @PostMapping("/sendMail")
    public String sendMail(
            @Valid @RequestBody EmailRequest details) throws MessagingException {

        return emailService.sendHtml(details);
    }


}
