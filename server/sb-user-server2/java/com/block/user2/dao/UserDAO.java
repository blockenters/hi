package com.block.user2.dao;

import com.block.user2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // CRUD 관련 함수들을 모두 구현해야 한다.

    // 유저 생성하는 함수
    public int createUser(User user){
        String sql = "INSERT INTO user (name, email)\n" +
                "values ( ? , ? );";
        return jdbcTemplate.update( sql, user.getName() , user.getEmail());
    }

}
