package com.block.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularResponse {

    public Long id;
    public String name;
    public String category;
    public Integer reviewCount;
    public Double averageRating;
    public String lastReviewDate;

}
