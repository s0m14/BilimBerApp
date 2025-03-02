package org.edu.bilimberapp.dto;

import lombok.Data;

@Data
public class UserInformation {
    private String username;
    private String email;
    private short phone;
    private String city;
    private int age;
}
