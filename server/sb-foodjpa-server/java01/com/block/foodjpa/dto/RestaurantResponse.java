package com.block.foodjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    public Long id;
    public String name;
    public String category;
    public String address;
    public String phone;
    public String description;
    public Double avgRating;
    public Integer reviewCount;
    public String createdAt;
}
