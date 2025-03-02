package org.edu.bilimberapp.controller;
import lombok.RequiredArgsConstructor;
import org.edu.bilimberapp.dto.UserInformation;
import org.edu.bilimberapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/user-info")
    public UserInformation getUserInformation() {
        return userService.getUserInformation();
    }
}
