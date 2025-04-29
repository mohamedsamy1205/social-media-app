package com.spring.social_media.services;


import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.spring.social_media.config.CustomUserDetails;
import com.spring.social_media.jwt.JwtUtil;
import com.spring.social_media.models.Users;
import com.spring.social_media.repository.UserRepositry;
import com.spring.social_media.side_classes.LoginRequest;
import com.spring.social_media.side_classes.UserRequest;

@Service
public class UserServices {
    @Autowired
    private UserRepositry userrepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void signIn(UserRequest u) throws IOException {
        userrepo.save(Users.builder()
            .username(u.getUsername())
            .password(u.getPassword())
            .name(u.getName())
            .role(u.getRole())
            .image(u.getImage().getBytes())
            .build());
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
                ));
            
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.getToken(user.getUsername());
        Users userModel = userrepo.findByusername(user.getUsername()).orElse(null);
        return ResponseEntity.ok(Map.of(
            "id", userModel.getId(),
            "username", userModel.getUsername(),
            "name", userModel.getName(),
            "role", userModel.getRole(),
            "image", userModel.getImage(),  
            "token", jwt
        ));

    } catch (BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    }

}
