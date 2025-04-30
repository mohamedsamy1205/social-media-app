package com.spring.social_media.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.social_media.models.notification.Notification;
import com.spring.social_media.repository.NotificationsRepository;

@Service
public class NotificitionServise {
    @Autowired
    private NotificationsRepository notificationRepository;
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

}
