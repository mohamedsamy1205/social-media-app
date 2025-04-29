package com.spring.social_media.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.services.UserServices;
import com.spring.social_media.side_classes.LoginRequest;
import com.spring.social_media.side_classes.UserRequest;

@RestController
@RequestMapping("/api/v1/reactions")
public class UserController {
    @Autowired
    private UserServices uServices;
    @PostMapping("/sign-in")
    public String creatuswr(@ModelAttribute UserRequest user) throws IOException {

        uServices.signIn(user);
        return "User is Created !";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return uServices.login(loginRequest);
    }


}
