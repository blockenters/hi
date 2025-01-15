package com.block.foodjpa.repository;

import com.block.foodjpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long > {
    Integer countByRestaurantId(long restaurantId);
    @Query("SELECT AVG(r.rating)\n" +
            "FROM Review r\n" +
            "WHERE r.restaurant.id = :restaurantId")
    Double averageRatingByRestaurantId(long restaurantId);
}
