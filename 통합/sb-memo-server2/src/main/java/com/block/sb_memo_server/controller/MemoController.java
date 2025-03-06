package com.block.sb_memo_server.controller;

import com.block.sb_memo_server.dto.MemoListRes;
import com.block.sb_memo_server.dto.MemoReq;
import com.block.sb_memo_server.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemoController {
    @Autowired
    MemoService memoService;

    @GetMapping("/memo")
    public ResponseEntity<MemoListRes> getMemoList(@RequestHeader("Authorization") String token,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size){

        MemoListRes res = memoService.getMemoList(token, page, size);
        return ResponseEntity.status(200).body(res);

    }

    @PostMapping("/memo")
    public ResponseEntity<Void> createMemo(@RequestHeader("Authorization") String token,
                                      @RequestBody MemoReq memoReq){

        memoService.createMemo(token, memoReq);
        return ResponseEntity.status(200).build();
    }

}
