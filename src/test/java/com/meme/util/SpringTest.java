package com.meme.util;

import com.meme.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class SpringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisTime() {
        Long redisTime = stringRedisTemplate.execute((RedisCallback<Long>) RedisServerCommands::time);
        log.info("System.currentTimeMillis: {}", System.currentTimeMillis());
        log.info("redisTime:                {}", redisTime);
    }

    @Autowired
    private DemoService demoService;

    @Test
    void testService() {
        log.info("v: {}", demoService.fun());
    }

    @Autowired
    @Qualifier("slidingWindowRateLimiter")
//    @Qualifier("fixedWindowRateLimiter")
    private com.meme.ratelimit.RateLimiter rateLimiter;

    @Test
    void testRateLimiter() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            log.info("{}", rateLimiter.acquire("aaaaa", 10, 1, 10, TimeUnit.SECONDS));
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

}
