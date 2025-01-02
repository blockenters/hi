package com.block.admin.controller;

import com.block.admin.dto.PopularListResponse;
import com.block.admin.dto.ReviewerListResponse;
import com.block.admin.dto.StatResponse;
import com.block.admin.service.CRMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRMController {

    @Autowired
    private CRMService crmService;

    @GetMapping("/api/v1/admin/crm/users/top-reviewers")
    public ResponseEntity<ReviewerListResponse> getTopReivewers(@RequestHeader("Authorization") String token,
                                                         @RequestParam("size") int size) {
       ReviewerListResponse reviewerListResponse =
               crmService.getTopReviewers(token, size);
       return ResponseEntity.status(200).body(reviewerListResponse);
    }

    @GetMapping("/api/v1/admin/crm/reviews/stats")
    public ResponseEntity<StatResponse> getStats(@RequestHeader("Authorization") String token,
                                          @RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate) {

        StatResponse statResponse = crmService.getStats(token, startDate, endDate);
        return ResponseEntity.status(200).body(statResponse);

    }

    @GetMapping("/api/v1/admin/crm/restaurants/popular")
    public ResponseEntity<PopularListResponse> getPopular(@RequestHeader("Authorization") String token,
                                                   @RequestParam(value = "category", required = false) String category,
                                                   @RequestParam(value = "minReviews", required = false) Integer minReviews) {

        PopularListResponse popularListResponse =
                crmService.getPopular(token, category, minReviews);

        return ResponseEntity.status(200).body(popularListResponse);

    }

}
