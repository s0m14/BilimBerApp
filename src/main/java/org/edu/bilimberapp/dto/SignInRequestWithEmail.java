package org.edu.bilimberapp.dto;

import lombok.Data;

@Data
public class SignInRequestWithEmail {
    private String email;
    private String password;
}
