package com.spring.social_media.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.social_media.config.CustomUserDetails;
import com.spring.social_media.models.Users;
import com.spring.social_media.repository.UserRepositry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustumUserDetailsService implements UserDetailsService {
        @Autowired
    private UserRepositry userRepositry;


    private final static String ROLE_PREFIX = "ROLE_";

    // The explicit constructor is removed as @RequiredArgsConstructor generates it automatically.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("UserName : {}", username);
        Optional<Users> user = userRepositry.findByusername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        log.info("User : {}", user.get());
        String password = user.get().getPassword();
//        log.info("Password{}", password);
        String role = user.get().getRole();
//        log.info("Role : {}", role);
        role= ROLE_PREFIX+role;
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));

        return new CustomUserDetails(username, password, roles);
    }
}
