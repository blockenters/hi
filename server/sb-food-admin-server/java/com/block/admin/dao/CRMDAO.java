package com.block.admin.dao;

import com.block.admin.dto.*;
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


    public List<DateResponse> getByDate(long userId, String startDate, String endDate){
        String sql = "SELECT date( created_at ) as date, \n" +
                "\t\t\tcount(*) reviewCount,  \n" +
                "\t\t\tavg(rating) averageRating\n" +
                "from review\n" +
                "where created_at BETWEEN ? and ? \n" +
                "group by date( created_at )\n" +
                "order by date asc;";
        return jdbcTemplate.query(sql, new DateRowMapper(), startDate, endDate);
    }

    public static class DateRowMapper implements RowMapper<DateResponse>{

        @Override
        public DateResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            DateResponse dateResponse = new DateResponse();
            dateResponse.date = rs.getString("date");
            dateResponse.reviewCount = rs.getInt("reviewCount");
            dateResponse.averageRating = rs.getDouble("averageRating");
            return dateResponse;
        }
    }



    public List<CategoryResponse> getByCategory(long userId, String startDate, String endDate){
        String sql = "SELECT rt.category  , \n" +
                "\t\t\tcount(*) reviewCount,  \n" +
                "\t\t\tavg( r.rating ) averageRating\n" +
                "from review r\n" +
                "join restaurant rt \n" +
                "on r.restaurant_id = rt.id\n" +
                "where r.created_at BETWEEN ? and ? \n" +
                "GROUP by rt.category\n" +
                "order by reviewCount desc;";
        return jdbcTemplate.query(sql, new CategoryRowMapper(), startDate, endDate);
    }

    public static class CategoryRowMapper implements RowMapper<CategoryResponse>{

        @Override
        public CategoryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.category = rs.getString("category");
            categoryResponse.reviewCount = rs.getInt("reviewCount");
            categoryResponse.averageRating = rs.getDouble("averageRating");
            return categoryResponse;
        }
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

    public List<PopularResponse> getPopular(){
        String sql = "SELECT r.id, r.name , r.category,\n" +
                "\t\t\tcount(rv.id) reviewCount, \n" +
                "\t\t\tavg(rv.rating) averageRating, \n" +
                "\t\t\tmax(rv.created_at) lastReviewDate\n" +
                "from restaurant r \n" +
                "join review rv \n" +
                "on r.id = rv.restaurant_id\n" +
                "group by r.id  \n" +
                "order by reviewCount desc;";
        return jdbcTemplate.query(sql, new PopularRowMapper() );
    }

    public static class PopularRowMapper implements RowMapper<PopularResponse>{

        @Override
        public PopularResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            PopularResponse popularResponse = new PopularResponse();
            popularResponse.id = rs.getLong("id");
            popularResponse.name = rs.getString("name");
            popularResponse.category = rs.getString("category");
            popularResponse.reviewCount = rs.getInt("reviewCount");
            popularResponse.averageRating = rs.getDouble("averageRating");
            popularResponse.lastReviewDate = rs.getString("lastReviewDate");
            return popularResponse;
        }
    }


}
