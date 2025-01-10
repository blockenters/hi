package com.block1.news.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    NaverApiConfig naverApiConfig;

    // 네이버 검색용 WebClient
    @Bean
    public WebClient naverWebClient(){
        return WebClient.builder()
                .baseUrl(naverApiConfig.newsUrl)
                .defaultHeader("X-Naver-Client-Id", naverApiConfig.clientId)
                .defaultHeader("X-Naver-Client-Secret", naverApiConfig.clientSecret)
                .build();
    }

    // 카카오 로그인 API 용 WebClient
//    public WebClient cacaoWebClient(){
//        return null;
//    }
//
//    // 유투브 검색 API 용 WebClient
//    public WebClient youtubeWebClient(){
//        return null;
//    }
}
