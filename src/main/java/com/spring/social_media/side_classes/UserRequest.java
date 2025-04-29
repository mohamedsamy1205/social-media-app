package com.spring.social_media.side_classes;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String role;
    private MultipartFile image;

}
