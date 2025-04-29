package com.spring.social_media.side_classes;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Long id;
    private String title;
    private Long postId;
    private Long userId; // Assuming this is a string representation of the media file (e.g., URL or path)
}
