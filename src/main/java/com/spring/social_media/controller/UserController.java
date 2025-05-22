package com.spring.social_media.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.models.Users;
import com.spring.social_media.repository.UserRepositry;
import com.spring.social_media.services.UserServices;
import com.spring.social_media.side_classes.LoginRequest;
import com.spring.social_media.side_classes.UserRequest;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserServices userService;
    @Autowired
    private UserRepositry userRepository;
    @PostMapping("/sign-in")
    public String creatuswr(@ModelAttribute UserRequest user) throws IOException {

        userService.signIn(user);
        return "User is Created !";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    @GetMapping("/userpic/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable Long userId) throws IOException {
       return ResponseEntity.ok()
               .header("Content-Type", "image/jpeg")
               .body(userService.getUserImage(userId));
    }
    

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId, @PathVariable String followingUsername) {
        Users followingUser = userRepository.findByusername(followingUsername).orElseThrow(() -> new RuntimeException("User not found"));
        Long followingId = followingUser.getId();
        userService.followUser(followerId, followingId);
        return ResponseEntity.ok("Followed successfully");
    }

    @PostMapping("/{followerId}/unfollow/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable String followingUsername) {
        Users followingUser = userRepository.findByusername(followingUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long followingId = followingUser.getId();
        userService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok("Unfollowed successfully");
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Set<Users>> getFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<Set<Users>> getFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowing(userId));
    }

    @GetMapping("/users/{userId}/followers-count")
    public ResponseEntity<Integer> getFollowersCount(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int followersCount = userService.getFollowersCount(user);
        return ResponseEntity.ok(followersCount);
    }

    @GetMapping("/users/{userId}/following-count")
    public ResponseEntity<Integer> getFollowingCount(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int followingCount = userService.getFollowingCount(user);
        return ResponseEntity.ok(followingCount);
    }
    
    @GetMapping("/users/{followerId}/is-following/{followingId}")
    public ResponseEntity<Boolean> isFollowing(
            @PathVariable Long followerId,
            @PathVariable String followingUsername) {

        boolean result = userService.isFollowing(followerId, followingUsername);
        return ResponseEntity.ok(result);
    }


}
