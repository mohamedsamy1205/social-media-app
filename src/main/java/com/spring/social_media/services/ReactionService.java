package com.spring.social_media.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.social_media.enums.ReactionType;
import com.spring.social_media.models.Posts;
import com.spring.social_media.models.Users;
import com.spring.social_media.models.reaction.Reactions;
import com.spring.social_media.repository.PostsRepositry;
import com.spring.social_media.repository.ReactionRepository;
import com.spring.social_media.repository.UserRepositry;

@Service
public class ReactionService {
    @Autowired
    private PostsRepositry postsRepositry;
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private ReactionRepository reactionRepository;

    public void addReaction(Long UserId, Long PostId, ReactionType reaction) {
        Users user = userRepositry.findById(UserId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + UserId));
        Posts post = postsRepositry.findById(PostId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + PostId));

        Reactions reactions = reactionRepository.findByPostIdAndUserId(PostId, UserId).orElse(Reactions.builder()
                .userModel(user)
                .posts(post)
                .build());
        reactions.setType(reaction);
        reactionRepository.save(reactions);
    }

    public void removeReaction(Long reactionId) {
        reactionRepository.deleteById(reactionId);
    }

    public List<Reactions> getAllReactionsByPostId(Long postId) {
        Posts post = postsRepositry.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return post.getReactions();
    }
    

}
