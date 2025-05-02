package com.spring.social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.models.notification.Notification;
import com.spring.social_media.services.NotificitionServise;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    @Autowired
    private NotificitionServise notificitionServise;


    @PutMapping("/notifications/{notificationId}/mark-as-read")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable Long notificationId) {
        notificitionServise.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificitionServise.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }
}
