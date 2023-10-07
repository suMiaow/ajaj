package com.meme.interceptor.config;

import com.meme.interceptor.MeInterceptorHandler;
import com.meme.interceptor.extd.MeStrInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InterceptorConfig {

    @Bean
    public MeInterceptorHandler<String, String> meInterceptorHandler(List<MeStrInterceptor> meInterceptorList) {
        return new MeInterceptorHandler<>(meInterceptorList);
    }
}
