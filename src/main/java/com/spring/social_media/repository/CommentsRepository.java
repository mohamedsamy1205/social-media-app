package com.spring.social_media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.reaction.Comments;

public interface CommentsRepository extends JpaRepository<Comments , Long> {
    Optional<Comments> findByPostsIdAndUsersId(Long postId, Long userId);
}
