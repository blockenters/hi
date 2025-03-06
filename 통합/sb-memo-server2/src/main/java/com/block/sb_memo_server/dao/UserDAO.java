package com.block.sb_memo_server.dao;

import com.block.sb_memo_server.dto.SignUpRequest;
import com.block.sb_memo_server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public User login(SignUpRequest signUpRequest){
        String sql = "select id, email, password, nickname\n" +
                "from user\n" +
                "where email = ?;";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), signUpRequest.email);

    }

    public static class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.id = rs.getLong("id");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.nickname = rs.getString("nickname");
            return user;
        }
    }

}
