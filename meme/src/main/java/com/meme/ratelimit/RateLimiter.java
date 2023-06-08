package com.meme.ratelimit;

import java.util.concurrent.TimeUnit;

public interface RateLimiter {

    /**
     * acquire the right to process in required time range
     *
     * @param key         identifier of the process
     * @param maxRequests max requests in one window time
     * @param windowSec   window time span in second
     * @param timeout     max blocking time before acquire the right
     * @param timeoutUnit time unit of the timeout
     * @return acquired
     */
    boolean acquire(String key, int maxRequests, long windowSec, long timeout, TimeUnit timeoutUnit);

    /**
     * acquire the right instantly
     *
     * @param key         identifier of the process
     * @param maxRequests max requests in one window time
     * @param windowSec   window time span in second
     * @return acquired
     */
    boolean acquire(String key, int maxRequests, long windowSec);
}
