package com.block.foodjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuResponse {
    public RestaurantResponse restaurant;
    public List<MenuResponse> menus;
}




