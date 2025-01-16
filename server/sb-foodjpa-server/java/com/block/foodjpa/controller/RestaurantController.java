package com.block.foodjpa.controller;

import com.block.foodjpa.dto.RestaurantListResponse;
import com.block.foodjpa.dto.RestaurantMenuResponse;
import com.block.foodjpa.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/api/v1/restaurants")
    public ResponseEntity<RestaurantListResponse> getAllRestaurants(@RequestParam("page") int page,
                                                             @RequestParam("size") int size,
                                                             @RequestParam(value = "category", required = false) String category,
                                                             @RequestParam(value = "keyword", required = false) String keyword){
       RestaurantListResponse response =
               restaurantService.getAllRestaurants(page, size, category, keyword);

       return ResponseEntity.status(200).body(response);
    }




    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantMenuResponse> getRestaurant(@PathVariable long id){
        try {
            RestaurantMenuResponse response =
                    restaurantService.getRestaurant(id);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}







