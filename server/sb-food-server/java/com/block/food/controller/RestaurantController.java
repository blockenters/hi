package com.block.food.controller;

import com.block.food.dto.RestaurantDetailResponse;
import com.block.food.dto.RestaurantListResponse;
import com.block.food.service.RestaurantService;
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
    public ResponseEntity<RestaurantListResponse> getRestaurants(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword){

        if( category == null && keyword == null){
            RestaurantListResponse restaurantListResponse =
                    restaurantService.getRestaurants(page, size);
            return ResponseEntity.status(200).body(restaurantListResponse);
        } else {
            // 카테고리가 있고 키워드는 없는 경우
            if( category != null && keyword == null){
                RestaurantListResponse restaurantListResponse =
                        restaurantService.getRestaurants(page, size, category, null);
                return ResponseEntity.status(200).body(restaurantListResponse);
            } else if(category == null && keyword != null){
                // 카테고리가 없고 키워드가 있는 경우

                RestaurantListResponse restaurantListResponse =
                        restaurantService.getRestaurants(page, size, null ,keyword);
                return ResponseEntity.status(200).body(restaurantListResponse);

            } else {
                // 카테고리와 키워드가 모두 있는 경우
                RestaurantListResponse restaurantListResponse =
                        restaurantService.getRestaurants(page, size, category, keyword);
                return ResponseEntity.status(200).body(restaurantListResponse);
            }

        }

    }

    @GetMapping("/api/v1/restaurants/{id}")
    public ResponseEntity<RestaurantDetailResponse> getRestaurantDetail(@PathVariable long id){
        RestaurantDetailResponse restaurantDetailResponse =
                restaurantService.getRestaurantDetail(id);
        return ResponseEntity.status(200).body(restaurantDetailResponse);
    }

}




