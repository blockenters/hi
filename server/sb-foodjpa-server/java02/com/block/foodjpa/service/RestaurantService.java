package com.block.foodjpa.service;

import com.block.foodjpa.dto.MenuResponse;
import com.block.foodjpa.dto.RestaurantMenuResponse;
import com.block.foodjpa.dto.RestaurantResponse;
import com.block.foodjpa.entity.Menu;
import com.block.foodjpa.entity.Restaurant;
import com.block.foodjpa.entity.Review;
import com.block.foodjpa.repository.MenuRepository;
import com.block.foodjpa.repository.RestaurantRepository;
import com.block.foodjpa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MenuRepository menuRepository;

    public RestaurantMenuResponse getRestaurant(long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()){
            throw new RuntimeException();
        }

        int reviewCount = reviewRepository.countByRestaurantId(id);
        double averageRating = reviewRepository.averageRatingByRestaurantId(id);

        List<Menu> menuList = menuRepository.findByRestaurantId(id);

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
        for( Menu menu  : menuList ){
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
