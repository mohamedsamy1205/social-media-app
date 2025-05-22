package com.spring.social_media.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.social_media.models.MediaFile;
import com.spring.social_media.repository.MediaRepository;
@Service
public class FilesService {
    @Autowired
    private MediaRepository mediaFileRepository;
    // @Autowired
    // private MediaFile mediaFile;

    public MediaFile saveFile(MultipartFile file) throws IOException {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFilename(file.getOriginalFilename());
        mediaFile.setContentType(file.getContentType());
        mediaFile.setData(file.getBytes());

        return mediaFileRepository.save(mediaFile);
    }
    public MediaFile getFile(Long id) {
        return mediaFileRepository.findById(id).orElse(null);
    }
}
