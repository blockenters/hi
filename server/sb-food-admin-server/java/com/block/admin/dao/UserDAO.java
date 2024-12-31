package com.block.admin.dao;

import com.block.admin.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int signUp(UserRequest userRequest){
        String sql = "INSERT INTO user (email, password, nickname, role)\n" +
                "values ( ? , ? , ? , 'ADMIN');";
        return jdbcTemplate.update(sql,
                userRequest.email,
                userRequest.password,
                userRequest.nickname);
    }

}
