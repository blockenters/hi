package com.block.travel.controller;

import com.block.travel.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @PostMapping("/api/v1/places/{placeId}/photos")
    public ResponseEntity<Void> uploadPhoto(@PathVariable long placeId,
                                       @RequestParam("photo") MultipartFile photo,
                                       @RequestParam(value = "description", required = false) String description ){
        try {
            photoService.uploadPhoto(placeId, photo, description);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }

    }

}
