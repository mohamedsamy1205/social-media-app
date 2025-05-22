package com.spring.social_media.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.social_media.services.FilesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/v1/files")

public class FilesController {
    @Autowired
    private FilesService filesService;

    @PostMapping("put")
    public String postMethodName(@ModelAttribute MultipartFile file) throws IOException {
        filesService.saveFile(file);
        return "File is uploaded successfully!";

    }
    @GetMapping("get/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) throws IOException {
        // MediaFile mediaFile = filesService.getFile(id);
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(filesService.getFile(id).getData());
    }
    
    
}
