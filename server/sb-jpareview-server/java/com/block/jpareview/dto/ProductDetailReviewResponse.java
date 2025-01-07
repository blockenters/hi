package com.block.jpareview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailReviewResponse {
    public ProductDetailResponse product;
    public List<ReviewResponse> recentReviews;
}
