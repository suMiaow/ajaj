package com.meme.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LimiterDemo {


    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingDeque<Object> queue = new LinkedBlockingDeque<>();

        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }

        RateLimiter rateLimiter = RateLimiter.create(4, 1, TimeUnit.SECONDS);

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(10);
                while (true) {
                    rateLimiter.acquire();
                    Object pop = queue.take();
                    log.info("i: {}", pop);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }).start();
        Thread.currentThread().join();
    }
}
