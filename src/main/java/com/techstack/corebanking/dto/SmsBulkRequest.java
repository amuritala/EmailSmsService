package com.techstack.corebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsBulkRequest {
    private String from  = "SkyAlert";
    private String content;
    private String callbackUrl;
    List<Recipients> recipients ;

}
