package com.techstack.corebanking.service;



import com.techstack.corebanking.dto.BulkSmsResponse;
import com.techstack.corebanking.dto.SmsBulkRequest;
import com.techstack.corebanking.dto.SmsRequest;
import com.techstack.corebanking.dto.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

import java.util.List;


@Service
public class  SmsService {

    //private final RestClient restClient;

    @Value("${api.wallet}")
    private String wallet;

    @Value("${api.authorization}")
    private String authorization;


    @Autowired
    private RestClient restClient ;

    public SmsResponse sendMessage(SmsRequest request) {

        return restClient.post()
                .uri("/v1/messages/sms")
                .header("X-Wallet", wallet)
                .header("Authorization", authorization)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(SmsResponse.class);
    }


    public SmsResponse[] sendBulkMessage(SmsBulkRequest request) {

        return restClient.post()
                .uri("/v1/messages/sms/bulk/otm")
                .header("X-Wallet", wallet)
                .header("Authorization", authorization)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(SmsResponse[].class);
    }

}