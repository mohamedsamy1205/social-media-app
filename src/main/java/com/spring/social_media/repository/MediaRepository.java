package com.spring.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.social_media.models.MediaFile;

public interface MediaRepository extends JpaRepository<MediaFile, Long> {
    List<MediaFile> findByPostsId(Long postId);

}
