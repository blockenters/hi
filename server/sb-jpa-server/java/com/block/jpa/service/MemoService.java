package com.block.jpa.service;

import com.block.jpa.dto.MemoRequest;
import com.block.jpa.entity.Memo;
import com.block.jpa.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {

    @Autowired
    MemoRepository memoRepository;

    public void createMemo(MemoRequest memoRequest){
        // DTO 를 Entity로 변환
        Memo memo = new Memo();
        memo.content = memoRequest.content;

        memoRepository.save(memo);
    }

}
