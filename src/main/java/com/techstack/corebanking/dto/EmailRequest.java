package com.techstack.corebanking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    @NotBlank
    @Email
    private String recipient;
    @NotBlank
    private String msgBody;
    @NotBlank
    private String subject;
    private String attachment;
}
