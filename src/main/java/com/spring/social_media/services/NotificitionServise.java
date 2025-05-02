package com.spring.social_media.services;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.social_media.models.Users;
import com.spring.social_media.models.notification.Notification;
import com.spring.social_media.repository.NotificationsRepository;
import com.spring.social_media.repository.UserRepositry;

@Service
public class NotificitionServise {
    @Autowired
    private NotificationsRepository notificationRepository;
    @Autowired
    private UserRepositry usersRepositry;

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        Users user = usersRepositry.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        return  notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }
    

    public String SendNotification(String message, Users user) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUser(user);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
        return "Notification sent successfully!";
    }

}

