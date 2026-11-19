package com.techstack.corebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponse {

    private double  cost;
    private String destination;
    private String Id;
    private String ticket;
    private String clientReference;
    private String status;


}
