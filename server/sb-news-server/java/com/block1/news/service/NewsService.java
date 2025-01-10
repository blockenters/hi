package com.block1.news.service;

import com.block1.news.config.NaverApiConfig;
import com.block1.news.dto.NaverNewResponse;
import com.block1.news.dto.NewsSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class NewsService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    NaverApiConfig naverApiConfig;

    @Autowired
    WebClient naverWebClient;
    // 카카오톡 API 호출용
    // WebClient cacaoWebClient;

    public NewsSearchResponse getNews2(String keyword){

        URI uri = UriComponentsBuilder
                .fromUriString(naverApiConfig.newsUrl)
                .queryParam("query", keyword)
                .queryParam("display", 20)
                .queryParam("start", 1)
                .queryParam("sort", "date")
                .build()
                .encode()
                .toUri();

        NaverNewResponse naverNewResponse = naverWebClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(NaverNewResponse.class)
                .block();

        // 우리 서버에 맞는 DTO로 변환
        NewsSearchResponse newsSearchResponse
                = new NewsSearchResponse();
        newsSearchResponse.totalCount = naverNewResponse.total;
        newsSearchResponse.articles = naverNewResponse.items;

        return newsSearchResponse;
    }



    public NewsSearchResponse getNews(String keyword){
        // 네이버의 뉴스 API를 호출하자.

        URI uri = UriComponentsBuilder
                .fromUriString(naverApiConfig.newsUrl)
                .queryParam("query", keyword)
                .queryParam("display", 20)
                .queryParam("start", 1)
                .queryParam("sort", "date")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("X-Naver-Client-Id" , naverApiConfig.clientId);
        headers.set("X-Naver-Client-Secret", naverApiConfig.clientSecret);

        NaverNewResponse naverNewResponse = restTemplate.exchange(uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                NaverNewResponse.class)
                .getBody();

        // 우리 서버에 맞는 DTO로 변환
        NewsSearchResponse newsSearchResponse
                = new NewsSearchResponse();
        newsSearchResponse.totalCount = naverNewResponse.total;
        newsSearchResponse.articles = naverNewResponse.items;

        return newsSearchResponse;

    }

}
