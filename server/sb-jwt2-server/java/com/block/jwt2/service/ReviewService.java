package com.block.jwt2.service;


import com.block.jwt2.config.JwtConfig;
import com.block.jwt2.dao.ReviewDAO;
import com.block.jwt2.dto.MyReviewListResponse;
import com.block.jwt2.dto.MyReviewResponse;
import com.block.jwt2.dto.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private JwtConfig jwtConfig;

    public int createReview(String token, long productId, ReviewRequest reviewRequest) {
        if( productId <= 0){
            return 1;
        }

        // useId 를 토큰에서 추출한다.
        // Bearer 를 제거하고 토큰을 추출한다.
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());
        reviewRequest.userId = userId;

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

    public int updateReview(String token, long reviewId, ReviewRequest reviewRequest){

        // 평점은 1~5 사이의 값이어야 한다.
        if( reviewRequest.rating < 1 || reviewRequest.rating > 5) {
            return 1;
        }

        // 내용은 10자 이상이어야 한다.
        if( reviewRequest.content == null || reviewRequest.content.length() < 10){
            return 2;
        }

        // userId 를 토큰에서 추출한다.
        // Bearer 를 제거하고 토큰을 추출한다.
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());
        reviewRequest.userId = userId;

        // db에 저장한다.
        int result = reviewDAO.updateReview(reviewId, reviewRequest);

        if(result == 0){
            // 리뷰아이디가 없거나 해당 유저의 리뷰가 아닌경우.
            return 3;
        }

        return 0;
    }

    public int deleteReview(String token, long reviewId){

        // userId 를 토큰에서 추출한다.
        // Bearer 를 제거하고 토큰을 추출한다.
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());

        int result = reviewDAO.deleteReview(reviewId, userId);
        if (result == 1){
            return 0;
        }else{
            return 1;
        }
    }

    public MyReviewListResponse getReviewListByUserId(String token, int page, int size){

        // userId 를 토큰에서 추출한다.
        // Bearer 를 제거하고 토큰을 추출한다.
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());


        int offset = (page - 1) * size;

        List<MyReviewResponse> myReviewList = reviewDAO.getReviewListByUserId(userId, offset, size);

        int totalElements = reviewDAO.getMyReviewTotalElements(userId);

        // totalPages
        int totalPages = (int) Math.ceil( totalElements / (double)size );

        int currentPage = page;
        int pageSize = size;

        // json 으로 처리할 클래스에 데이터를 담는다.
        MyReviewListResponse myReviewListResponse = new MyReviewListResponse(myReviewList, totalPages, totalElements, currentPage, pageSize);

        return myReviewListResponse;

    }

}





