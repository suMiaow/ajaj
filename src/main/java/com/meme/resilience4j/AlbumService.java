package com.meme.resilience4j;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumService {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public String getAlbumList() {
        RestTemplate restTemplate = new RestTemplate();
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "https://jsonplaceholder.typicode.com/albums";
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .
//                .build();

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class));
    }
}
