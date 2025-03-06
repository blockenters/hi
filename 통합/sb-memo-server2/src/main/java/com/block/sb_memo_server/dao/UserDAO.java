package com.block.sb_memo_server.dao;

import com.block.sb_memo_server.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int signUp(SignUpRequest signUpRequest){
        String sql = "insert into user \n" +
                "(email, password, nickname)\n" +
                "values\n" +
                "( ? , ? ,  ? );";
        return jdbcTemplate.update(sql, signUpRequest.email, signUpRequest.password, signUpRequest.nickname);
    }

}
