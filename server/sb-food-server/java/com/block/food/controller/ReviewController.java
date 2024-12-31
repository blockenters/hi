package com.block.food.controller;

import com.block.food.dto.ReviewRequest;
import com.block.food.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/api/v1/reviews")
    public ResponseEntity<Void> createReview(@RequestHeader("Authorization") String token,
                                        @RequestBody ReviewRequest reviewRequest){
        int result = reviewService.createReview(token, reviewRequest);
        if (result != 1 ){
            // result 가 1이라는 얘기는, 테이블에 행 하나 추가가 되었다는 뜻
            // 따라서 1이 아니라는 것은, 문제가 있는 경우다.
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).build();
    }

}




