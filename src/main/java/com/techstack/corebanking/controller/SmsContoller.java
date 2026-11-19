package com.techstack.corebanking.controller;


import com.techstack.corebanking.dto.BulkSmsResponse;
import com.techstack.corebanking.dto.SmsBulkRequest;
import com.techstack.corebanking.dto.SmsRequest;
import com.techstack.corebanking.dto.SmsResponse;
import com.techstack.corebanking.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class SmsContoller {

    @Autowired
    private SmsService smsService ;

    @PostMapping("/api/vi/sendsms")

    public SmsResponse sendMessage(
            @RequestBody SmsRequest request) {

        return smsService.sendMessage(request);
    }

    @PostMapping("/api/vi/sendbulksms")
    public  SmsResponse[] sendBulkMessage(
            @RequestBody SmsBulkRequest request) {
          SmsResponse[] response = smsService.sendBulkMessage(request);
        return response;
        ///  return ResponseEntity.ok(smsService.sendBulkMessage(request));
    }




}
