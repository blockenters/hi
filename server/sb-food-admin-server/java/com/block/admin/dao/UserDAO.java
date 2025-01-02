package com.block.admin.dao;

import com.block.admin.dto.UserRequest;
import com.block.admin.enetity.User;
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

    public int signUp(UserRequest userRequest){
        String sql = "INSERT INTO user (email, password, nickname, role)\n" +
                "values ( ? , ? , ? , 'ADMIN');";
        return jdbcTemplate.update(sql,
                userRequest.email,
                userRequest.password,
                userRequest.nickname);
    }

    public User login(UserRequest userRequest){
        String sql = "select *\n" +
                "from user\n" +
                "where email = ? and role = 'ADMIN';";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userRequest.email);
    }

    public static class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.id = rs.getLong("id");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.nickname = rs.getString("nickname");
            user.role = rs.getString("role");
            user.createdAt = rs.getString("created_at");
            return user;
        }
    }

}
