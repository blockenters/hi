package com.block.posting.dao;

import com.block.posting.entity.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostingDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int createPosting(long userId, String imgUrl, String content){
        String sql = "insert into posting \n" +
                "(user_id, img_url, content)\n" +
                "values\n" +
                "( ? , ? , ? );";
        return jdbcTemplate.update(sql, userId, imgUrl, content);
    }

    public List<Posting> getPostingList(long userId, int offset, int size) {
        String sql = "select p.*, u.email, if(l.id is null, 0, 1) as is_like\n" +
                "from follow f\n" +
                "join posting p\n" +
                "on f.followee_id = p.user_id\n" +
                "join user u \n" +
                "on f.followee_id = u.id\n" +
                "left join likes l \n" +
                "on l.user_id = ? and p.id = l.posting_id\n" +
                "where follower_id = ? \n" +
                "order by created_at desc\n" +
                "limit ?, ? ;";
        return jdbcTemplate.query(sql, new PostingRowMapper(), userId, userId, offset, size );
    }

    private class PostingRowMapper implements RowMapper<Posting> {
        @Override
        public Posting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Posting posting = new Posting();
            posting.id = rs.getLong("id");
            posting.userId = rs.getLong("user_id");
            posting.email = rs.getString("email");
            posting.imgUrl = rs.getString("img_url");
            posting.content = rs.getString("content");
            posting.createdAt = rs.getString("created_at");
            posting.isLike = rs.getInt("is_like");
            return posting;
        }
    }
}
