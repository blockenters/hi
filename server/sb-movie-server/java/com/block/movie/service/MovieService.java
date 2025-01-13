package com.block.movie.service;

import com.block.movie.config.MovieConfig;
import com.block.movie.dto.BoxOfficeResponse;
import com.block.movie.dto.DailyBoxOffice;
import com.block.movie.dto.DailyBoxOfficeResponse;
import com.block.movie.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieConfig movieConfig;

    @Autowired
    RestTemplate restTemplate;

    public DailyBoxOfficeResponse getMovies(String date){
        // 영화진흥원 API 를 호출.
        // Uri 를 만든다.
        URI uri = UriComponentsBuilder
                .fromUriString(movieConfig.url)
                .queryParam("key", movieConfig.apiKey)
                .queryParam("targetDt", date)
                .queryParam("multiMovieYn", "N")
                .build()
                .encode()
                .toUri();
        // 헤더 셋팅은 없으니까 헤더는 셋팅 안한다.
        HttpHeaders headers = new HttpHeaders();

        BoxOfficeResponse boxOfficeResponse = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(headers), BoxOfficeResponse.class)
                .getBody();

        // 영화진흥원으로부터 데이터를 받아왔으니까,
        // 우리 서버에 필요한 정보만 뽑아서 처리한다.
        DailyBoxOfficeResponse dailyBoxOfficeResponse =
                new DailyBoxOfficeResponse();
        dailyBoxOfficeResponse.date = date;

        ArrayList<Movie> movieList = new ArrayList<>();
        for( DailyBoxOffice boxOffice : boxOfficeResponse.boxOfficeResult.dailyBoxOfficeList){
            Movie movie = new Movie();

            movie.rank = boxOffice.rank;
            movie.rankInten = boxOffice.rankInten;
            movie.movieCd = boxOffice.movieCd;
            movie.movieName = boxOffice.movieNm;
            movie.openDate = boxOffice.openDt;
            movie.salesAcc = boxOffice.salesAcc;
            movie.audiAcc = boxOffice.audiAcc;
            movieList.add(movie);
        }

        dailyBoxOfficeResponse.movies = movieList;
        return dailyBoxOfficeResponse;
    }


}
