package com.block.admin.service;

import com.block.admin.config.JwtConfig;
import com.block.admin.dao.CRMDAO;
import com.block.admin.dto.ReviewerListResponse;
import com.block.admin.dto.ReviewerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRMService {
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    private CRMDAO crmDAO;

    public ReviewerListResponse getTopReviewers(String token, int size) {
        long userId = Long.parseLong( jwtConfig.getTokenClaims(token.substring(7)).getSubject() );
        List<ReviewerResponse> reviewerList = crmDAO.getTopReviewers(userId, size);
        return new ReviewerListResponse(reviewerList);
    }
}
