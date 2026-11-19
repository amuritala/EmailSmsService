package com.techstack.corebanking.controller;


import com.techstack.corebanking.dto.EmailRequest;
import com.techstack.corebanking.service.EmailService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;


    // Send simple email
    @PostMapping("/sendMail")
    public String sendMail(
            @RequestBody EmailRequest details) throws MessagingException {

        return emailService.sendHtml(details);
    }


}
