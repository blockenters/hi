package com.block.food.service;

import com.block.food.config.JwtConfig;
import com.block.food.dao.ReviewDAO;
import com.block.food.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    JwtConfig jwtConfig;
    @Autowired
    ReviewDAO reviewDAO;

    public int createReview(String token, ReviewRequest reviewRequest){
        // 토큰은 Bearer (한칸공백포함) 뒷부분만 토큰이다.
        Long userId = Long.parseLong( jwtConfig.getTokenClaims(token.substring(7) ).getSubject() );

        return reviewDAO.createReview(userId, reviewRequest);

    }

}
