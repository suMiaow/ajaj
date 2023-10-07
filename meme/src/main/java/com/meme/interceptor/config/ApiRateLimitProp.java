package com.meme.interceptor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "api-rate")
@EnableConfigurationProperties(ApiRateLimitProp.class)
@Data
public class ApiRateLimitProp {

    private Integer defaultRate = 100;

    private Map<String, Integer> defaultApiRate;

}
