package com.block.foodjpa.service;

import com.block.foodjpa.dto.MenuResponse;
import com.block.foodjpa.dto.RestaurantMenuResponse;
import com.block.foodjpa.dto.RestaurantResponse;
import com.block.foodjpa.entity.Menu;
import com.block.foodjpa.entity.Restaurant;
import com.block.foodjpa.entity.Review;
import com.block.foodjpa.repository.MenuRepository;
import com.block.foodjpa.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantMenuResponse getRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()){
            throw new RuntimeException();
        }
        // averageRating , reviewCount 를 구해보자.
        int reviewCount = restaurant.get().reviewList.size();
        int total = 0;
        for( Review review  : restaurant.get().reviewList){
            total = total + review.rating;
        }
        double averageRating = (double) total / reviewCount;

        RestaurantMenuResponse response =
                new RestaurantMenuResponse();

        RestaurantResponse restaurantResponse =
                new RestaurantResponse();
        restaurantResponse.id = restaurant.get().id;
        restaurantResponse.name = restaurant.get().name;
        restaurantResponse.category = restaurant.get().category;
        restaurantResponse.address = restaurant.get().address;
        restaurantResponse.phone = restaurant.get().phone;
        restaurantResponse.description = restaurant.get().description;
        restaurantResponse.avgRating = averageRating;
        restaurantResponse.reviewCount = reviewCount;
        restaurantResponse.createdAt = restaurant.get().createdAt.toString();

        response.restaurant = restaurantResponse;

        ArrayList<MenuResponse> menuResponseArrayList =
                                                new ArrayList<>();
        for( Menu menu  : restaurant.get().menuList ){
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.id = menu.id;
            menuResponse.name = menu.name;
            menuResponse.price = menu.price;
            menuResponse.description = menu.description;
            menuResponse.category = menu.category;
            menuResponse.reviewCount = menu.reviewList.size();
            menuResponseArrayList.add(menuResponse);
        }

        response.menus = menuResponseArrayList;
        return response;
    }

}
