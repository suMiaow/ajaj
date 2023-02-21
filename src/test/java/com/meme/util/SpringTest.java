package com.meme.util;

import com.google.common.util.concurrent.RateLimiter;
import com.meme.service.DemoService;
import com.meme.temp.handler.ServiceHandler;
import com.meme.temp.handler.model.request.DemoRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class SpringTest {

    @Autowired
    private RateLimiter rateLimiter;

    @Test
    void testLimiter() throws InterruptedException {

        LinkedBlockingDeque<Object> queue = new LinkedBlockingDeque<>();

        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }

        while (true) {
//            rateLimiter.acquire();
            if (rateLimiter.tryAcquire()) {
                Object take = queue.take();
                log.info("take: {}", take);
            } else {
                TimeUnit.SECONDS.sleep(1);
            }
        }

    }

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
        log.info("v: {}", demoService.fun() );
    }

}
