package com.block.foodjpa.repository;

import com.block.foodjpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long > {

    @Query("SELECT AVG(r.rating)\n" +
            "FROM Review r\n" +
            "WHERE r.restaurant.id = :id")
    Double avgRatingByRestaurantId(long id);

}
