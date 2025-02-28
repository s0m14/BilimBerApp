package org.edu.bilimberapp.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private short phone;
}
