package com.block.foodjpa.controller;

import com.block.foodjpa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/api/v1/reviews/restaurant/{restaurantId}/menu/{menuId}")
    public ResponseEntity<Void> createReview(@RequestHeader("Authorization") String token,
                 @PathVariable long restaurantId,
                 @PathVariable long menuId,
                 @RequestParam("rating") String strRating,
                 @RequestParam("content") String content,
                 @RequestParam(value = "image", required = false) MultipartFile image){
        try {
            reviewService.createReview(token, restaurantId,
                                menuId, strRating, content, image);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }


    }
}
