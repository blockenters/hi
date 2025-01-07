package com.block.jpareview.repository;

import com.block.jpareview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // JPA를 이용한 query 에는, 테이블명=> 클래스 이름,  컬럼명=> 멤버변수로 작성.
    // SQL 맨 끝에 ; 는 적지 않는다.
    @Query("select avg( rating ) from Review  where product.id = :productId")
    public double findAverageRatingByProductId(long productId);

    public int countByProductId(long productId);

    public List<Review> findTop5ByProductIdOrderByCreatedAtDesc(long productId);
}
