package com.spring.social_media.models.reaction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.social_media.enums.ReactionType;
import com.spring.social_media.models.Posts;
import com.spring.social_media.models.Users;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ReactionType type;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts posts;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userModel;
}
