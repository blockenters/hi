package com.block.review.dao;

import com.block.review.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int createUser(UserRequest userRequest){
        String sql = "insert into users (email, password, nickname)\n" +
                "values ( ? , ? , ? );";
        return jdbcTemplate.update(sql, userRequest.email, userRequest.password, userRequest.nickname);
    }

}
