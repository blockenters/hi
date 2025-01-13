package com.block.movie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieConfig {
    @Value("${movie.api.key}")
    public String apiKey;
    @Value("${movie.api.url}")
    public String url;

}
