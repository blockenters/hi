package com.block.sb_memo_server.dao;

import com.block.sb_memo_server.dto.MemoReq;
import com.block.sb_memo_server.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemoDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Memo> getMemoList(long userId, int offset, int limit){
        String sql = "select id, user_id, title, content, memo_date, created_at\n" +
                "from memo\n" +
                "where user_id = ? \n" +
                "limit  ? , ? ;";
        return jdbcTemplate.query(sql, new MemoRowMapper(), userId, offset, limit);
    }

    public static class MemoRowMapper implements RowMapper<Memo> {
        @Override
        public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Memo memo = new Memo();
            memo.id = rs.getLong("id");
            memo.userId = rs.getLong("user_id");
            memo.title = rs.getString("title");
            memo.content = rs.getString("content");
            memo.memoDate = rs.getString("memo_date");
            memo.createdAt = rs.getString("created_at");
            return memo;
        }
    }


    public int createMemo(long userId, MemoReq memoReq){
        String sql = "insert into memo \n" +
                "(user_id, title, content, memo_date)\n" +
                "values\n" +
                "(  ? , ? , ? , ? );";
        return jdbcTemplate.update(sql, userId, memoReq.title, memoReq.content, memoReq.memoDate);
    }

}
