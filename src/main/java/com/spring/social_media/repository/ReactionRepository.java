package com.spring.social_media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.reaction.Reactions;

public interface ReactionRepository extends JpaRepository<Reactions, Long> {
    Optional<Reactions> findByPostsIdAndUserModelId(Long postId, Long userId);

}
