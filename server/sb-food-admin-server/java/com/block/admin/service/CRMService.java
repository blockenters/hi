package com.block.admin.service;

import com.block.admin.config.JwtConfig;
import com.block.admin.dao.CRMDAO;
import com.block.admin.dto.*;
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

    public StatResponse getStats(String token, String startDate, String endDate){
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());
        TotalResponse totalResponse = crmDAO.getTotal(userId, startDate, endDate);
        List<DateResponse> dateList = crmDAO.getByDate(userId, startDate, endDate);
        List<CategoryResponse> categoryList = crmDAO.getByCategory(userId, startDate, endDate);

        StatResponse statResponse = new StatResponse(totalResponse,
                                                    dateList, categoryList);
        return statResponse;
    }
}
