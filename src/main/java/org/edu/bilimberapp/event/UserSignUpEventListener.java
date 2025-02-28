package org.edu.bilimberapp.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSignUpEventListener {
    private final JavaMailSender javaMailSender;

    @EventListener
    public void onUserSignUpEvent(UserSignUpEvent userSignUpEvent) {
        String email = userSignUpEvent.getEmail();
        String verificationCode = userSignUpEvent.getVerificationCode();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Verification Code");
        mailMessage.setText("Welcome to BilimBer App. Your verification code is: " + verificationCode);
        mailMessage.setFrom("arsen.ospanov14111986@gmail.com");
        javaMailSender.send(mailMessage);

    }
}
