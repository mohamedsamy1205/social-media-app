package com.spring.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.Users;
import com.spring.social_media.models.notification.Notification;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(Users user);

}
