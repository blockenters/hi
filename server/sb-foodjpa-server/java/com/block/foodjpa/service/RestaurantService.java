package com.block.foodjpa.service;

import com.block.foodjpa.dto.*;
import com.block.foodjpa.entity.Menu;
import com.block.foodjpa.entity.Restaurant;
import com.block.foodjpa.entity.Review;
import com.block.foodjpa.repository.MenuRepository;
import com.block.foodjpa.repository.RestaurantRepository;
import com.block.foodjpa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ReviewRepository reviewRepository;

    public RestaurantListResponse getAllRestaurants(int page, int size, String category, String keyword){

        PageRequest pageRequest = PageRequest.of(page -1, size);
        Page<Restaurant> restaurantPage = null;

        if(category == null && keyword != null){
           restaurantPage =
                    restaurantRepository.findByNameContainsOrAddressContains(keyword, keyword, pageRequest);
        }else if(category == null && keyword == null){

        }else if(category != null && keyword == null){

        }else{
            restaurantPage =
                    restaurantRepository.findByCategoryAndNameContainsOrCategoryAndAddressContains(category, keyword, category, keyword, pageRequest);
        }

        ArrayList<RestaurantResponse> restaurantResponseArrayList =
                new ArrayList<>();

        for( Restaurant restaurant : restaurantPage){
            RestaurantResponse restaurantResponse =
                    new RestaurantResponse();
            restaurantResponse.id = restaurant.id;
            restaurantResponse.name = restaurant.name;
            restaurantResponse.category = restaurant.category;
            restaurantResponse.phone = restaurant.phone;
            restaurantResponse.address = restaurant.address;
            restaurantResponse.description = restaurant.description;
            restaurantResponse.createdAt = restaurant.createdAt.toString();
            // 리뷰 갯수랑 리뷰 평점은 따로 가져온다.
            restaurantResponse.reviewCount = restaurant.reviewList.size();

            restaurantResponse.avgRating = reviewRepository.avgRatingByRestaurantId(restaurant.id);

            restaurantResponseArrayList.add(restaurantResponse);
        }

        PageableResponse pageableResponse =
                new PageableResponse(page, size, restaurantPage.getTotalElements(), restaurantPage.getTotalPages());

        RestaurantListResponse response =
                new RestaurantListResponse(restaurantResponseArrayList, pageableResponse);
        return response;


    }


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
