package com.block.user2.dao;

import com.block.user2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public User getUserById(long id){
        String sql = "SELECT *\n" +
                "from user\n" +
                "where id = ? ;";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    public List<User> getAllUsers(){
        String sql = "SELECT *\n" +
                "from user;";
        return jdbcTemplate.query(sql, new UserRowMapper() );
    }



    private static class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId( rs.getLong("id")  );
            user.setName( rs.getString("name")  );
            user.setEmail( rs.getString("email") );
            user.setCreatedAt( rs.getString("created_at") );
            return user;
        }
    }

}
