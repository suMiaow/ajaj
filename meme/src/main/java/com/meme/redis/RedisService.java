package com.meme.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    public void aaa() {
        String key = "aaa";
        Long l = redisTemplate.opsForValue().increment(key, 101L);
        System.out.println(l);
    }
}
