package org.edu.bilimberapp.service;

import lombok.RequiredArgsConstructor;
import org.edu.bilimberapp.dto.UserInformation;
import org.edu.bilimberapp.entity.User;
import org.edu.bilimberapp.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User create(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("Username not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Email not found"));
    }

    public User getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public User updateVerificationState(boolean verified,User user){
        user.setVerified(verified);
        return userRepository.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public UserInformation getUserInformation(){
        var userInfo = new UserInformation();
        userInfo.setUsername(getCurrentUser().getUsername());
        userInfo.setEmail(getCurrentUser().getEmail());
        userInfo.setPhone(getCurrentUser().getPhone());
        userInfo.setCity(getCurrentUser().getCity());
        userInfo.setAge(getCurrentUser().getAge());
        return userInfo;
    }
}