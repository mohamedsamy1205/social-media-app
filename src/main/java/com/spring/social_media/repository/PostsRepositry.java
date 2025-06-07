package com.spring.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.Posts;

public interface PostsRepositry extends JpaRepository<Posts, Long> {
    List<Posts> findByUserId(Long id);

    List<Posts> findByUsername(String username);

}
