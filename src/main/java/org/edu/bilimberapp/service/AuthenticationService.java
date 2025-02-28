package org.edu.bilimberapp.service;

import lombok.RequiredArgsConstructor;
import org.edu.bilimberapp.dto.*;
import org.edu.bilimberapp.entity.User;
import org.edu.bilimberapp.event.UserSignUpEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void signUp(SignUpRequest signUpRequest) {
        int coder = (int)(Math.random() * 1000000);
        String code = String.valueOf(coder);

        var user = User.builder().username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phone(signUpRequest.getPhone())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail()).
                verificationCode(code).
                build();
        userService.create(user);
        applicationEventPublisher.publishEvent(new UserSignUpEvent(this,signUpRequest.getEmail(),code));
    }

    public JwtAuthenticationResponse verifyUser(VerificationCode verificationCode) {
        var user = userService.getByEmail(verificationCode.getEmail());
        if(verificationCode.getCode().equals(user.getVerificationCode())){
            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        }
        else{
            return null;
        }
    }

    public JwtAuthenticationResponse signInWithUsername(SignInRequestWithUsername sign) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                sign.getUsername(), sign.getPassword()
        ));

        var user = userService.userDetailsService().loadUserByUsername(sign.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signInWithEmail(SignInRequestWithEmail sign) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                sign.getEmail(), sign.getPassword()
        ));

        var user =  userService.userDetailsService().loadUserByUsername(sign.getEmail());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}