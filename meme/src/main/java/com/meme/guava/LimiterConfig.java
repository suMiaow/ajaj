package com.meme.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class LimiterConfig {

    @Value("${permitsPerSecond:1}")
    private Double permitesPerSecond;

    @Value("${warmupPeriod:5000}")
    private Long warmupPeriod;

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(permitesPerSecond, warmupPeriod, TimeUnit.MILLISECONDS);
//        return RateLimiter.create(permitesPerSecond);
    }
}
