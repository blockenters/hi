package com.block.foodjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantListResponse {
    public List<RestaurantResponse> content;
    public PageableResponse pageable;
}
