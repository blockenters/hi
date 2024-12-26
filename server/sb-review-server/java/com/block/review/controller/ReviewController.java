package com.block.review.controller;

import com.block.review.dto.ReviewRequest;
import com.block.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/products/{productId}/reviews")
    public ResponseEntity<Object> createReview(@PathVariable long productId,
                                        @RequestBody ReviewRequest reviewRequest) {

        int result = reviewService.createReview(productId, reviewRequest);

        if(result == 1 || result == 2){
            return ResponseEntity.status(404).build();
        } else if(result == 3 || result == 4 || result == 5){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).build();

    }

}





