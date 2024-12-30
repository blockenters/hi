package com.block.food.controller;

import com.block.food.dto.RestaurantListResponse;
import com.block.food.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/api/v1/restaurants")
    public ResponseEntity<RestaurantListResponse> getRestaurants(@RequestParam int page, @RequestParam int size){

        RestaurantListResponse restaurantListResponse =
                restaurantService.getRestaurants(page, size);

        return ResponseEntity.status(200).body(restaurantListResponse);

    }
}
