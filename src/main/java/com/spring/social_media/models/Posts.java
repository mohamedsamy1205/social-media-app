package com.spring.social_media.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.social_media.models.reaction.Comments;
import com.spring.social_media.models.reaction.Reactions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "username" )
    private String username;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username", insertable = false, updatable = false)
    private Users userModel;
    @OneToMany(mappedBy = "posts")
    private List<MediaFile> mediaFile;
    @OneToMany(mappedBy = "posts")
    @Builder.Default
    private List<Reactions> reactions = new ArrayList<>();
    @OneToMany(mappedBy = "posts")
    @Builder.Default
    private List<Comments> comments = new ArrayList<>();
}
