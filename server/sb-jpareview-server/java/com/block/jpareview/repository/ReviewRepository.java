package com.block.jpareview.repository;

import com.block.jpareview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public double findAverageRatingByProductId(long productId);
    public int countByProductId(long productId);
}
