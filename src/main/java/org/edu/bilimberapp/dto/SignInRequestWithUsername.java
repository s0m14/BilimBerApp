package org.edu.bilimberapp.dto;

import lombok.Data;

@Data
public class SignInRequestWithUsername {

    private String username;
    private String password;
}
