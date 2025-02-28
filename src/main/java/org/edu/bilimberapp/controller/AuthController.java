package org.edu.bilimberapp.controller;

import lombok.RequiredArgsConstructor;
import org.edu.bilimberapp.dto.JwtAuthenticationResponse;
import org.edu.bilimberapp.dto.SignInRequestWithEmail;
import org.edu.bilimberapp.dto.SignUpRequest;
import org.edu.bilimberapp.dto.VerificationCode;
import org.edu.bilimberapp.service.AuthenticationService;
import org.edu.bilimberapp.service.JwtService;
import org.edu.bilimberapp.service.UserService;
import org.edu.bilimberapp.service.VerificationEmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final VerificationEmailService verificationEmailService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/verify")
    public JwtAuthenticationResponse verifyCode(@RequestBody VerificationCode verificationCode) {
        return authenticationService.verifyUser(verificationCode);
    }

    @PostMapping("/sign-in-mail")
    public JwtAuthenticationResponse signInWithMail(@RequestBody SignInRequestWithEmail signInRequest) {
        return authenticationService.signInWithEmail(signInRequest);
    }

    @PostMapping("/sign-in-username")
    public JwtAuthenticationResponse signInWithUsername(@RequestBody SignInRequestWithEmail signInRequest) {
        return authenticationService.signInWithEmail(signInRequest);
    }
}
