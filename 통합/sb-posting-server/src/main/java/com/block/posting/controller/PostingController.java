package com.block.posting.controller;

import com.block.posting.dto.PostingListRes;
import com.block.posting.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PostingController {

    @Autowired
    PostingService postingService;

    @PostMapping("/posting")
    public ResponseEntity<Void> createPosting(@RequestHeader("Authorization") String token,
                                         @RequestParam("image") MultipartFile image,
                                         @RequestParam("content") String content){
        try {
            postingService.createPosting(token, image, content);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/posting")
    public ResponseEntity<PostingListRes> getPostingList(@RequestHeader("Authorization") String token,
                                                  @RequestParam int page,
                                                  @RequestParam int size){
        PostingListRes res = postingService.getPostingList(token, page, size);
        return ResponseEntity.status(200).body(res);
    }

}
