package org.edu.bilimberapp.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserSignUpEvent extends ApplicationEvent {

    private String email;
    private String verificationCode;

    public UserSignUpEvent(Object source, String email, String verificationCode) {
        super(source);
        this.email = email;
        this.verificationCode = verificationCode;
    }
}