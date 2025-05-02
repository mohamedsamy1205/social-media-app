package com.spring.social_media.models.notification;

import java.time.LocalDateTime;

import com.spring.social_media.models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "notification")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean isRead = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private LocalDateTime createdAt = LocalDateTime.now();
}


