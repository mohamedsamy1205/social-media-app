package com.spring.social_media.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.spring.social_media.models.Posts;
import com.spring.social_media.services.PostsServices;
import com.spring.social_media.side_classes.PostRequest;
import com.spring.social_media.side_classes.UpdatePostRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/reactions")
public class PostsController {
    @Autowired
    private PostsServices services;

    
    @PostMapping("/add-post")
    public String addpost(@ModelAttribute PostRequest post) throws IOException {
        services.savePostes(post);
        return "post is added !";
    }
    






























    @GetMapping("/findbyusername")
    public List<Posts> findbyusername(@RequestParam String username) {

        return services.find(username);
    }
    @GetMapping("/delete-post")
    public String deletebyId(@RequestParam Long id) {
        services.deletePostesById(id);
        return "post is deleted !";
    }







    @PostMapping("/update-post")
    public String updatePostes(@RequestBody UpdatePostRequest post ) {
        services.updatePost(post);

        return "Post is UpDated !";
    }






    
    @GetMapping("/findall")
    public List<Posts> findall() {
        return services.findall();
    }
    

}
