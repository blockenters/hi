package com.block.jpareview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    public Long id;
    public String name;
    public String description;
    public Integer price;
    public String category;
    public Integer stockQuantity;
    public String createdAt;
    public Double averageRating;
    public Integer reviewCount;

}
