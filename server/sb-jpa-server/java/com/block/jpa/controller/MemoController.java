package com.block.jpa.controller;

import com.block.jpa.dto.MemoRequest;
import com.block.jpa.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {

    @Autowired
    MemoService memoService;

    @PostMapping("/memo")
    public ResponseEntity<Void> createMemo(@RequestBody MemoRequest memoRequest){
        memoService.createMemo(memoRequest);
        return ResponseEntity.status(201).build();
    }
}





