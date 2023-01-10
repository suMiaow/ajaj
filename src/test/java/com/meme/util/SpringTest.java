package com.meme.util;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
