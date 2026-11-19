package com.techstack.corebanking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
