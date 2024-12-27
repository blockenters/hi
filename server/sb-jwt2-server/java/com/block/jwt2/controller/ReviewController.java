package com.block.jwt2.controller;


import com.block.jwt2.dto.MyReviewListResponse;
import com.block.jwt2.dto.ReviewRequest;
import com.block.jwt2.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/products/{productId}/reviews")
    public ResponseEntity<Object> createReview(@RequestHeader("Authorization") String token,
                                        @PathVariable long productId,
                                        @RequestBody ReviewRequest reviewRequest) {

        int result = reviewService.createReview(token, productId, reviewRequest);

        if(result == 1 || result == 2){
            return ResponseEntity.status(404).build();
        } else if(result == 3 || result == 4 || result == 5){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(201).build();

    }
    @PutMapping("/api/reviews/{reviewId}")
    public ResponseEntity<Object> updateReview(@RequestHeader("Authorization") String token,
                                        @PathVariable long reviewId,
                                        @RequestBody ReviewRequest reviewRequest){

        int result = reviewService.updateReview(token, reviewId, reviewRequest);

        if(result == 1 || result == 2){
            return ResponseEntity.status(400).build();
        }else if(result == 3){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).build();
    }


    @DeleteMapping("/api/reviews/{reviewId}")
    public ResponseEntity<Object> deleteReview(@RequestHeader("Authorization") String token,
                                        @PathVariable long reviewId){

        int result = reviewService.deleteReview(token, reviewId);

        if(result == 0){
            return ResponseEntity.status(204).build();
        }else {
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/api/users/me/reviews")
    public ResponseEntity<MyReviewListResponse> getReviewListByUserId(@RequestHeader("Authorization") String token,
                                                                      @RequestParam(defaultValue = "1") int page,
                                                                      @RequestParam(defaultValue = "10") int size){

        MyReviewListResponse myReviewListResponse = reviewService.getReviewListByUserId(token, page, size);
        return ResponseEntity.status(200).body(myReviewListResponse);
    }

}





