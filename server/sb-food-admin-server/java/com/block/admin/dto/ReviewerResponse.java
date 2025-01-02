package com.block.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewerResponse {
    public Long userId;
    public String nickname;
    public Integer reviewCount;
    public Double averageRating;
    public String lastReviewDate;
}
