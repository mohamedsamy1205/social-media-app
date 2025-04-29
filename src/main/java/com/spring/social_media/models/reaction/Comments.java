package com.spring.social_media.models.reaction;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.social_media.models.MediaFile;
import com.spring.social_media.models.Posts;
import com.spring.social_media.models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    private Long id;
    private String commentText;
    private byte[] data;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts posts;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;
    @OneToMany(mappedBy = "comments")
    private MediaFile mediaFile;
}
