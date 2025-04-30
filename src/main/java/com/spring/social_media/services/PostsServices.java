package com.spring.social_media.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.social_media.models.MediaFile;
import com.spring.social_media.models.Posts;
import com.spring.social_media.models.Users;
import com.spring.social_media.repository.MediaRepository;
import com.spring.social_media.repository.PostsRepositry;
import com.spring.social_media.repository.UserRepositry;
import com.spring.social_media.side_classes.PostRequest;
import com.spring.social_media.side_classes.UpdatePostRequest;

import java.io.IOException;


@Service
public class PostsServices {
    @Autowired
    private PostsRepositry postsRepositry;
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private MediaRepository mediarepo;

    public void savePostes(PostRequest p) throws IOException{
        Posts post = new Posts();
        post.setTitle(p.getTitle());
        post.setUsername(p.getUsername());

        List<MediaFile> files = new ArrayList<>();
        for (MultipartFile file : p.getFiles()) {
            MediaFile mediaFile = new MediaFile();
            mediaFile.setFilename(file.getOriginalFilename());
            mediaFile.setContentType(file.getContentType());
            mediaFile.setSize(file.getSize());
            mediaFile.setData(file.getBytes());
            mediaFile.setPosts(post); // Set the post for the media file
            files.add(mediaFile);
        }
        post.setMediaFile(files);
        postsRepositry.save(post); // Set the media files for the post
        mediarepo.saveAll(files); // Save all media files
        
    }


    public Posts findByPostesId(Long id) {
        return postsRepositry.findById(id).orElse(null);
    }

    public void deletePostesById(Long id) {
        postsRepositry.deleteById(id);
    }

    public void updatePost(UpdatePostRequest postRequest) {
        Posts post = postsRepositry.findById(postRequest.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Users user = userRepositry.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // تحقق انه صاحب البوست أو أدمن
        if (!post.getUserModel().getId().equals(user.getId()) && !user.getRole().equals("ADMIN")) {
            throw new RuntimeException("You are not authorized to update this post");
        }

        post.setTitle(postRequest.getTitle());
        postsRepositry.save(post);
    }

    public List<Posts> findall() {
        return postsRepositry.findAll();
    }
    public List<Posts> find(String username) {
        return postsRepositry.findByusername(username);
    }

}
