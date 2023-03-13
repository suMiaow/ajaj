package com.meme.ratelimit.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class SlidingWindowRateLimiter extends AbstractRateLimiter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean acquire(String key, int maxRequests, long windowSec) {

        String script = "local key = KEYS[1]; " +
                "local max_requests = tonumber(ARGV[1]); " +
                "local window = tonumber(ARGV[2]); " +
                "local current_time = redis.call('TIME'); " +
                "local trim_time = tonumber(current_time[1]) - window; " +
                "redis.call('ZREMRANGEBYSCORE', key, 0, trim_time); " +
                "local request_count = redis.call('ZCARD', key); " +
                "if request_count < max_requests then " +
                "    redis.call('ZADD', key, current_time[1], current_time[1] .. current_time[2]); " +
                "    redis.call('EXPIRE', key, window); " +
                "    return true; " +
                "end; " +
                "return false; ";
        RedisScript<Boolean> slidingWindowScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean acquired = stringRedisTemplate.execute(slidingWindowScript, Collections.singletonList(key), String.valueOf(maxRequests), String.valueOf(windowSec));
        return BooleanUtils.isTrue(acquired);
    }

}
