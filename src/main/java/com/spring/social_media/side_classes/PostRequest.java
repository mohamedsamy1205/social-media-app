package com.spring.social_media.side_classes;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String username;
    private MultipartFile [] files;

}
