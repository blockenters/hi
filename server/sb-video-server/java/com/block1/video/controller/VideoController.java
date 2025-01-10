package com.block1.video.controller;

import com.block1.video.dto.VideoListResponse;
import com.block1.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/api/v1/video/search")
    public ResponseEntity<VideoListResponse> getVideos(@RequestParam("keyword") String keyword){

       VideoListResponse videoListResponse =
               videoService.getVideos(keyword);
       return ResponseEntity.status(200).body(videoListResponse);

    }
}
