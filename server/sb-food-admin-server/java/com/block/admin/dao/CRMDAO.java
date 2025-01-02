package com.block.admin.dao;

import com.block.admin.dto.ReviewerResponse;
import com.block.admin.dto.TotalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CRMDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReviewerResponse> getTopReviewers(long userId, int size) {
        String sql = "select u.id, u.nickname, \n" +
                "\t\tcount(r.id) reviewCount, \n" +
                "\t    ROUND( IFNULL( avg(r.rating) , 0 ) , 2) averageRating  , \n" +
                "\t\tmax( r.created_at ) lastReviewDate\n" +
                "from user u \n" +
                "left join review r \n" +
                "on u.id = r.user_id\n" +
                "where role = 'USER'\n" +
                "group by u.id \n" +
                "order by reviewCount desc, averageRating desc\n" +
                "limit ? ;";
        return jdbcTemplate.query(sql, new ReviewerRowMapper(), size );
    }


    public TotalResponse getTotal(long userId, String startDate, String endDate){
        String sql = "SELECT  count(*) reviewCount , avg(rating) averageRating\n" +
                "from review\n" +
                "where created_at BETWEEN ? and ?;";
        return jdbcTemplate.queryForObject(sql, new TotalRowMapper(), startDate, endDate);
    }

    public static class TotalRowMapper implements RowMapper<TotalResponse>{

        @Override
        public TotalResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            TotalResponse totalResponse = new TotalResponse();
            totalResponse.reviewCount = rs.getInt("reviewCount");
            totalResponse.averageRating = rs.getDouble("averageRating");
            return totalResponse;
        }
    }


    getByDate(){

    }

    getByCategory(){

    }



    public static class ReviewerRowMapper implements RowMapper<ReviewerResponse>{

        @Override
        public ReviewerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReviewerResponse reviewerResponse = new ReviewerResponse();
            reviewerResponse.userId = rs.getLong("id");
            reviewerResponse.nickname = rs.getString("nickname");
            reviewerResponse.reviewCount = rs.getInt("reviewCount");
            reviewerResponse.averageRating = rs.getDouble("averageRating");
            reviewerResponse.lastReviewDate = rs.getString("lastReviewDate");
            return reviewerResponse;
        }
    }
}
