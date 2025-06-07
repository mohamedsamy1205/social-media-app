package com.spring.social_media.services;


import java.io.IOException;

import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
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


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServices {
    @Autowired
    private UserRepositry userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private NotificitionServise notificitionServise;
    @Autowired
    private AuthenticationManager authenticationManager;

    // private String imageType;
    public void signIn(UserRequest u) throws IOException {

        // if (u.getImage().isEmpty()) {
        // ClassPathResource resource = new ClassPathResource(
        // "E:\\projects\\Spring boot projects\\social media
        // App\\src\\main\\resources\\static\\user-image.png");
        // byte[] defaultBytes = StreamUtils.copyToByteArray(resource.getInputStream());
        // filesService.saveFile(defaultBytes);
        // return;
        // }

        Users user = new Users();
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setName(u.getName());
        user.setRole(u.getRole());
        if (u.getImage() != null && !u.getImage().isEmpty()) {

            user.setImage(u.getImage().getBytes());
        } else {
            ClassPathResource resource = new ClassPathResource(
                    "static/user-image.png");
            byte[] defaultBytes = StreamUtils.copyToByteArray(resource.getInputStream());
            user.setImage(defaultBytes);

        }
        userRepository.save(user);

    }


    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.getToken(user.getUsername());
            Users userModel = userRepository.findByusername(user.getUsername()).orElse(null);
            return ResponseEntity.ok(Map.of(
                    "id", userModel.getId(),
                    "username", userModel.getUsername(),
                    "name", userModel.getName(),
                    "role", userModel.getRole(),
                    // "image", userModel.getImage(),
                    "token", jwt));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    public byte[] getUserImage(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return user.getImage();
        
    }



    public void followUser(Long followerId, Long followingId) {
        Users follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));
        Users following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User to follow not found"));

        follower.getFollowing().add(following);
        userRepository.save(follower);
        notificitionServise.SendNotification(follower.getName() + " started following you!", following);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        Users follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));
        Users following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("User to unfollow not found"));

        follower.getFollowing().remove(following);
        userRepository.save(follower);
        notificitionServise.SendNotification(follower.getName() + " stopped following you!", following);
    }

    public Set<Users> getFollowers(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers();
    }

    public Set<Users> getFollowing(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing();
    }

    public int getFollowersCount(Users user) {
        return user.getFollowers().size();
    }

    public int getFollowingCount(Users user) {
        return user.getFollowing().size();
    }
    
    public boolean isFollowing(Long followerId, String followingUsername) {
        Users follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        Users following = userRepository.findByusername(
                followingUsername)
                .orElseThrow(() -> new RuntimeException("Following not found"));

        return follower.getFollowing().contains(following);
    }




}
