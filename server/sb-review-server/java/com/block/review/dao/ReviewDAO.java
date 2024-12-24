package com.block.review.dao;

import com.block.review.dto.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ReviewResponse> getReviewListByProductId(long productId){
        String sql = "SELECT r.id , r.user_id, u.nickname, r.rating, r.content, r.created_at\n" +
                "FROM reviews r\n" +
                "join users u\n" +
                "on r.user_id = u.id\n" +
                "where product_id =  ?  \n" +
                "order by r.created_at desc ;";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), productId);
    }

    public static class ReviewRowMapper implements RowMapper<ReviewResponse>{

        @Override
        public ReviewResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.id = rs.getLong("id");
            reviewResponse.userId = rs.getLong("user_id");
            reviewResponse.nickname = rs.getString("nickname");
            reviewResponse.rating = rs.getInt("rating");
            reviewResponse.content = rs.getString("content");
            reviewResponse.createdAt = rs.getString("created_at");
            return reviewResponse;
        }
    }

}



