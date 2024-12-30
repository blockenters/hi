package com.block.food.dao;

import com.block.food.dto.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RestaurantDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<RestaurantResponse> getRestaurants(int offset, int size) {
        String sql = "SELECT r.id, r.name, r.category, r.address, r.phone, r.description, \n" +
                "\t\t\tIFNULL(  avg(rv.rating) , 0)  avg_rating   ,\n" +
                "\t\t\tcount(rv.id)  review_count,\n" +
                "\t\t\tr.created_at\n" +
                "from restaurant r\n" +
                "left join review  rv\n" +
                "on r.id = rv.restaurant_id\n" +
                "group by r.id \n" +
                "order by r.created_at desc\n" +
                "limit ? ,  ?  ;";
        return jdbcTemplate.query(sql, new RestaurantRowMapper(), offset, size);
    }


    public int getTotalElements(){
        String sql = "SELECT count(*)\n" +
                "from restaurant;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }





    public static class RestaurantRowMapper implements RowMapper<RestaurantResponse> {

        @Override
        public RestaurantResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            RestaurantResponse restaurantResponse = new RestaurantResponse();
            restaurantResponse.id = rs.getLong("id");
            restaurantResponse.name = rs.getString("name");
            restaurantResponse.category = rs.getString("category");
            restaurantResponse.address = rs.getString("address");
            restaurantResponse.phone = rs.getString("phone");
            restaurantResponse.description = rs.getString("description");
            restaurantResponse.avgRating = rs.getDouble("avg_rating");
            restaurantResponse.reviewCount = rs.getInt("review_count");
            restaurantResponse.createdAt = rs.getString("created_at");
            return restaurantResponse;
        }
    }

}
