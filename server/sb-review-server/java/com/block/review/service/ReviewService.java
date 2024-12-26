package com.block.review.service;

import com.block.review.dao.ReviewDAO;
import com.block.review.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public int createReview(long productId, ReviewRequest reviewRequest) {
        if( productId <= 0){
            return 1;
        }
        if( reviewRequest.userId <= 0) {
            return 2;
        }
        if( reviewRequest.rating < 1 || reviewRequest.rating > 5) {
            return 3;
        }
        if( reviewRequest.content == null || reviewRequest.content.length() < 10){
            return 4;
        }

        //  리뷰를 db 에 저장한다.
        try {
            reviewDAO.createReview(productId, reviewRequest);
        } catch (Exception e) {
            // 동일한 제품에 동일한 사람이 리뷰를 또 남기려고 하면
            // DB 에러가 발생한다.
            return 5;
        }

        return 0;
    }

}
