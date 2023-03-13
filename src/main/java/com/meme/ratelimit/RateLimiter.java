package com.meme.ratelimit;

import java.util.concurrent.TimeUnit;

public interface RateLimiter {

    boolean acquire(String key, int maxRequests, long windowSec, long timout, TimeUnit timoutUnit);

    boolean acquire(String key, int maxRequests, long windowSec);
}
