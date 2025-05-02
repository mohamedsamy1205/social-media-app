package com.spring.social_media.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.social_media.models.Posts;
import com.spring.social_media.models.Users;
import com.spring.social_media.models.reaction.Comments;
import com.spring.social_media.repository.CommentsRepository;
import com.spring.social_media.repository.PostsRepositry;
import com.spring.social_media.repository.UserRepositry;

public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private PostsRepositry postsRepositry;
    @Autowired
    private UserRepositry usersRepositry;
    @Autowired
    private NotificitionServise notificitionServise;

    public void addComment(Long postId, Long userId, String comment) {
        Posts post = postsRepositry.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Users userOfPost = usersRepositry.findByusername(post.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        Users user = usersRepositry.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Comments newComment = commentsRepository.findByPostsIdAndUsersId(postId, userId).orElse(Comments.builder()
                .posts(post)
                .users(user)
                .build());
        newComment.setCommentText(comment);
        newComment.setPosts(post);
        notificitionServise.SendNotification( user.getName()+"New comment on your post", userOfPost);
    }

    public void deleteComment(Long commentId) {
        commentsRepository.deleteById(commentId);
    }

    public void updateComment(Long commentId, String newCommentText) {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setCommentText(newCommentText);
    }
    public List<Comments> getAllCommentsByPostId(Long postId) {
        Posts posts = postsRepositry.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return posts.getComments();
    }

}
