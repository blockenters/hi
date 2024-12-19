package com.block.user.dao;

import com.block.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO {
    // 데이터베이스에 쿼리할 SQL을 실행하는 클래스
    // CRUD 기능을 제공한다.

    // SQL문을 실행하는 클래스.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // User 테이블에 insert 함수
    public int createUser(User user){
        String sql = "INSERT INTO user (name, email)\n" +
                "values ( ? , ? );";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail() );
    }

}





