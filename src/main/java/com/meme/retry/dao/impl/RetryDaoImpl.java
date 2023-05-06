package com.meme.retry.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meme.retry.dao.RetryDao;
import com.meme.retry.model.RetryInfo;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class RetryDaoImpl implements RetryDao {

    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public RetryDaoImpl(
            StringRedisTemplate stringRedisTemplate,
            ObjectMapper objectMapper
    ) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    private String appCode = "appCode";

    @SneakyThrows
    @Override
    public void save(RetryInfo retryInfo) {
        String retryId = retryInfo.getUniqueKey();
        String nextRetryTimeMillis = String.valueOf(retryInfo.getNextRetryTimeMillis());
        String retryDetail = objectMapper.writeValueAsString(retryInfo);
        String retryQueueKey = getRetryQueueKey();
        String retryDetailKey = getRetryDetailKey(retryId);

        String scriptStr = """
                local queue_key = KEYS[1];
                local detail_key = KEYS[2];
                local retry_id = ARGV[1];
                local next_retry_time = tonumber(ARGV[2]);
                local retry_detail = ARGV[3];

                redis.call("ZADD", queue_key, next_retry_time, retry_id);
                redis.call("SET", detail_key, retry_detail);
                """;

        RedisScript<Void> redisScript = new DefaultRedisScript<>(scriptStr, Void.class);
        stringRedisTemplate.execute(redisScript, Arrays.asList(retryQueueKey, retryDetailKey), retryId, nextRetryTimeMillis, retryDetail);
    }

    @SneakyThrows
    @Override
    public List<RetryInfo> peek() {
        List<RetryInfo> retryInfoList = new ArrayList<>();

        String retryQueueKey = getRetryQueueKey();
        Set<String> retryIds = stringRedisTemplate.opsForZSet().rangeByScore(retryQueueKey, 0, System.currentTimeMillis());

        if (CollectionUtils.isNotEmpty(retryIds)) {
            for (String retryId : retryIds) {
                String retryInfoStr = stringRedisTemplate.opsForValue().get(getRetryDetailKey(retryId));
                if (StringUtils.isNotBlank(retryInfoStr)) {
                    retryInfoList.add(objectMapper.readValue(retryInfoStr, RetryInfo.class));
                }
            }
        }
        return retryInfoList;
    }


    @Override
    public void delete(RetryInfo retryInfo) {

        String retryId = retryInfo.getUniqueKey();
        String retryQueueKey = getRetryQueueKey();
        String retryDetailKey = getRetryDetailKey(retryId);

        String scriptStr = """
                local queue_key = KEYS[1];
                local detail_key = KEYS[2];
                local retry_id = ARGV[1];
                                
                redis.call("ZREM", queue_key, retry_id);
                redis.call("DEL", detail_key);
                """;

        RedisScript<Void> redisScript = new DefaultRedisScript<>(scriptStr, Void.class);
        stringRedisTemplate.execute(redisScript, Arrays.asList(retryQueueKey, retryDetailKey), retryId);
    }

    @Override
    public void moveToDLQ(RetryInfo retryInfo) {
        String retryId = retryInfo.getUniqueKey();
        String startTimeMillis = String.valueOf(retryInfo.getStartTimeMillis());
        String retryQueueKey = getRetryQueueKey();
        String retryDLQKey = getRetryDLQKey();

        String scriptStr = """
                local queue_key = KEYS[1];
                local dlq_key = KEYS[2];
                local retry_id = ARGV[1];
                local retry_start_time = tonumber(ARGV[2]);
                    
                redis.call("ZADD", dlq_key, retry_start_time, retry_id);
                redis.call("ZREM", queue_key, retry_id);
                """;

        RedisScript<Void> redisScript = new DefaultRedisScript<>(scriptStr, Void.class);
        stringRedisTemplate.execute(redisScript, Arrays.asList(retryQueueKey, retryDLQKey), retryId, startTimeMillis);
    }

    private String getRetryQueueKey() {
        return appCode + ":retry:queue";
    }

    private String getRetryDetailKey(String id) {
        return appCode + ":retry:detail:" + id;
    }

    private String getRetryDLQKey() {
        return appCode + ":retry:dlq";
    }
}
