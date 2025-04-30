package com.spring.social_media.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.social_media.models.reaction.Comments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String filename;
    private String contentType;
    private Long size;
    @Lob
    @Column(name = "file_data", length = 1000000)
    private byte[] data;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts posts;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users userModel;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "comments_id", referencedColumnName = "id")
    private Comments comments;
    

}
