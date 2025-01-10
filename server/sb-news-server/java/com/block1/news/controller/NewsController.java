package com.block1.news.controller;

import com.block1.news.dto.NewsSearchResponse;
import com.block1.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/api/v1/news/search")
    public ResponseEntity<NewsSearchResponse> getNews(@RequestParam("keyword") String keyword ){
       NewsSearchResponse newsSearchResponse =
               newsService.getNews(keyword);
       return ResponseEntity.status(200).body(newsSearchResponse);
    }

}


