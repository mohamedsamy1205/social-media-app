package com.spring.social_media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.Users;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<Users,Long> {
    Optional<Users> findByusername(String username) ;
}
