package com.block.movie.controller;

import com.block.movie.dto.DailyBoxOfficeResponse;
import com.block.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/movies/rank")
    public ResponseEntity<DailyBoxOfficeResponse> getMovies(@RequestParam("date") String date){
       DailyBoxOfficeResponse dailyBoxOfficeResponse =
               movieService.getMovies(date);
       return ResponseEntity.status(200).body(dailyBoxOfficeResponse);
    }
}







