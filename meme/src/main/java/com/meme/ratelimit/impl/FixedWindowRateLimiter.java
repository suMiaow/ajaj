package com.meme.ratelimit.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class FixedWindowRateLimiter extends AbstractRateLimiter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean acquire(String key, int maxRequests, long windowSec) {

        String script = "local key = KEYS[1]; " +
                "local requests = tonumber(redis.call('GET', key) or '-1'); " +
                "local max_requests = tonumber(ARGV[1]); " +
                "local expiry = tonumber(ARGV[2]); " +
                "if (requests == -1) or (requests < max_requests) then " +
                "    redis.call('INCR', key); " +
                "    local ttl = tonumber(redis.call('TTL', key)); " +
                "    if (ttl == -1) then " +
                "        redis.call('EXPIRE', key, expiry); " +
                "    end " +
                "    return true; " +
                "else " +
                "    return false; " +
                "end ";
        RedisScript<Boolean> slidingWindowScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean acquired = stringRedisTemplate.execute(slidingWindowScript, Collections.singletonList("adapter:rate_limit:" + key), String.valueOf(maxRequests), String.valueOf(windowSec));
        return BooleanUtils.isTrue(acquired);
    }
}
