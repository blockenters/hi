package com.block.food.service;

import com.block.food.dao.RestaurantDAO;
import com.block.food.dto.PageableResponse;
import com.block.food.dto.RestaurantListResponse;
import com.block.food.dto.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantDAO restaurantDAO;

    public RestaurantListResponse getRestaurants(int page, int size){
        // SQL에서 limit을 사용하여 페이징 처리를 해야함
        int offset = (page - 1) * size;

        List<RestaurantResponse> restaurantList = restaurantDAO.getRestaurants(offset, size);

        int totalElements = restaurantDAO.getTotalElements();
        int totalPages = (int) Math.ceil( (double) totalElements / size );

        PageableResponse pageableResponse = new PageableResponse(page, size, totalElements,totalPages);

        RestaurantListResponse restaurantListResponse =
                new RestaurantListResponse(restaurantList, pageableResponse);

        return restaurantListResponse;

    }
}
