package com.meme.ratelimit.impl;

import com.meme.ratelimit.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractRateLimiter implements RateLimiter {
    @Override
    public boolean acquire(String key, int maxRequests, long windowSec, long timeout, TimeUnit timeoutUnit) {
        boolean acquired = false;
        long beginTimeMillis = System.currentTimeMillis();
        long timeoutMillis = timeoutUnit.toMillis(timeout);
        try {
            do {
                acquired = acquire(key, maxRequests, windowSec);
                if (!acquired && System.currentTimeMillis() < beginTimeMillis + timeoutMillis) {
                    TimeUnit.MILLISECONDS.sleep(windowSec * 1000 / maxRequests);
                }
            } while (!acquired && System.currentTimeMillis() < beginTimeMillis + timeoutMillis);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        return acquired;
    }

    @Override
    public boolean acquire(String key, int maxRequests, long windowSec) {
        return false;
    }
}
