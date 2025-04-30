package com.spring.social_media.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.social_media.models.notification.Notification;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "passeord", unique = true)
    private String password;
    @Lob
    @Column(name = "image", length = 100000)
    private byte[] image;
    private String role;
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "userModel" )
    private List<Posts> posts;
    @OneToMany(mappedBy = "userModel")
    @JsonBackReference
    private List<MediaFile> mediaFile;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
        name = "user_following",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    @Builder.Default
    private Set<Users> following = new HashSet<>();
    @ManyToMany(mappedBy = "following")
    @JsonBackReference
    @Builder.Default
    private Set<Users> followers = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @Builder.Default
    private List<Notification> notifications = new ArrayList<>();

}
