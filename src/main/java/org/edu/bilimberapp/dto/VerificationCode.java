package org.edu.bilimberapp.dto;

import lombok.Data;

@Data
public class VerificationCode {
    private String email;
    private String code;
}
