package com.block.foodjpa.repository;

import com.block.foodjpa.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

     Page<Restaurant> findByNameContainsOrAddressContains(String keyword1, String keyword2, Pageable pageable);

     Page<Restaurant> findByCategoryAndNameContainsOrCategoryAndAddressContains(String category1, String keyword1, String category2, String keyword2, Pageable pageable);
}
