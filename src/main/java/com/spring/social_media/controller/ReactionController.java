package com.spring.social_media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.enums.ReactionType;
import com.spring.social_media.models.reaction.Reactions;
import com.spring.social_media.services.ReactionService;

@RestController
@RequestMapping("/api/v1/reactions")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;

    @PostMapping("/add-reaction")
    public String addReaction(@RequestParam Long userId, @RequestParam Long postId,
            @RequestParam ReactionType reaction) {
        reactionService.addReaction(userId, postId, reaction);
        return "Reaction added successfully!";
    }
    @GetMapping("/{reactionId}")
    public void removeReaction(@PathVariable Long reactionId) {
        reactionService.removeReaction(reactionId);
    }

    @GetMapping("/post/{postId}")
    public List<Reactions> getReactions(@PathVariable Long postId) {
        return reactionService.getAllReactionsByPostId(postId);
    }
}
