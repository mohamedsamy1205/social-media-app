package com.spring.social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.models.Users;
import com.spring.social_media.models.notification.Notification;
import com.spring.social_media.repository.NotificationsRepository;
import com.spring.social_media.repository.UserRepositry;
import com.spring.social_media.services.NotificitionServise;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    @Autowired
    private NotificitionServise notificitionServise;
    @Autowired
    private NotificationsRepository notificationRepository;
    @Autowired
    private UserRepositry userRepository;


    @PutMapping("/notifications/{notificationId}/mark-as-read")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable Long notificationId) {
        notificitionServise.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Notification> notifications = notificationRepository.findByUserOrderByCreatedAtDesc(user);
        return ResponseEntity.ok(notifications);
    }
}
