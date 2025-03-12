package com.block.posting.dao;

import com.block.posting.dto.SignUpReq;
import com.block.posting.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int signUp(SignUpReq signUpReq){
        String sql = "insert into user \n" +
                "(email, password)\n" +
                "values \n" +
                "(? , ? );";
        return jdbcTemplate.update(sql, signUpReq.email, signUpReq.password);
    }

    public User login(SignUpReq signUpReq) {
        String sql = "select id, email, password\n" +
                "from user\n" +
                "where email = ? ;";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), signUpReq.email);
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            User user = new User();
            user.id = rs.getLong("id");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            return user;
        }
    }

}
