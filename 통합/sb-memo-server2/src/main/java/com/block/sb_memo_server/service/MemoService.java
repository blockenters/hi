package com.block.sb_memo_server.service;

import com.block.sb_memo_server.config.JwtConfig;
import com.block.sb_memo_server.dao.MemoDAO;
import com.block.sb_memo_server.dto.MemoListRes;
import com.block.sb_memo_server.dto.MemoReq;
import com.block.sb_memo_server.entity.Memo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    MemoDAO memoDAO;

    public MemoListRes getMemoList(String token, int page, int size){
        // 1. 토큰에서 userId 뽑는다.
        long userId = Long.parseLong( jwtConfig.getTokenClaims(token.substring(7)).getSubject());
        // 2. 페이징 처리하기 위해서 offset 과 limit을 계산한다.
        int offset = (page - 1) * size;
        int limit = size;
        // 3. DB에서 메모 리스트를 가져온다.
        List<Memo> memoList = memoDAO.getMemoList(userId, offset, limit);

        // 4. DTO클래스 만들어서 리턴한다.
        int count = memoList.size();
        MemoListRes memoListRes = new MemoListRes(memoList, count);
        return memoListRes;
    }


    public void createMemo(String token, MemoReq memoReq){
        // 1. 토큰에서 userId 뽑는다.
        Claims claims = jwtConfig.getTokenClaims( token.substring(7) );
        String strUserId = claims.getSubject();
        long userId = Long.parseLong(strUserId);

        // 2. DB에 저장한다.
        memoDAO.createMemo(userId, memoReq);
    }

}
