package com.meme.temp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meme.beans.service.DemoService;
import com.meme.beans.service.impl.AaaDemoService;
import com.meme.event.CustomSpringEventPublisher;
import com.meme.interceptor.MeInterceptorHandler;
import com.meme.interceptor.model.HandleResult;
import com.meme.retry.model.RetryInfo;
import com.meme.retry.service.RetryService;
import com.meme.util.AjAjTestUtils;
import com.meme.util.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisTime() {
        Long redisTime = stringRedisTemplate.execute((RedisCallback<Long>) RedisServerCommands::time);
        log.info("System.currentTimeMillis: {}", System.currentTimeMillis());
        log.info("redisTime:                {}", redisTime);
    }


    @Autowired
    @Qualifier("slidingWindowRateLimiter")
//    @Qualifier("fixedWindowRateLimiter")
    private com.meme.ratelimit.RateLimiter rateLimiter;

    @Test
    void testRateLimiter() throws InterruptedException {
        log.info(rateLimiter.getClass().getSimpleName());
        for (int i = 0; i < 100; i++) {
            log.info("{}", rateLimiter.acquire("aaaaa", 10, 1, 10, TimeUnit.SECONDS));
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    @Autowired
    private CustomSpringEventPublisher publisher;

    @Test
    void testEvent() {
        publisher.publishCustomEvent();

    }

    @Autowired
    private RetryService retryService;

    @Test
    void testRetry() throws InterruptedException {

        RetryInfo retryInfo1 = new RetryInfo().toBuilder()
                .stepList(List.of(1L, 2L, 4L, 8L, 16L, 30L, 60L))
                .timeoutSpan(5L)
                .timeoutSpanUnit(TimeUnit.MINUTES)
                .build();

        RetryInfo retryInfo2 = new RetryInfo().toBuilder()
                .stepList(List.of(1L, 2L, 4L, 8L, 16L, 30L, 60L))
                .timeoutSpan(5L)
                .timeoutSpanUnit(TimeUnit.MINUTES)
                .build();

        retryService.saveRetry(retryInfo1);

        while (true) {

            retryService.retry();

            TimeUnit.SECONDS.sleep(2L);
        }

    }

    @Test
    void testLeftPushScript() {
        String scriptStr = AjAjTestUtils.readFile("lua/left_push.lua");

        RedisScript<Object> redisScript = new DefaultRedisScript<>(scriptStr);
        stringRedisTemplate.execute(redisScript, Arrays.asList("test:ids", "test:queue"), "2");
    }

    @Test
    void testRightPopScript() {

        String scriptStr = AjAjTestUtils.readFile("lua/right_pop.lua");

        RedisScript<String> redisScript = new DefaultRedisScript<>(scriptStr, String.class);
        String result = stringRedisTemplate.execute(redisScript, Arrays.asList("test:queue", "test:detail:"));
//
        log.info("result: {}", result);
    }

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void testLazada() {

        String fileName = "misc/lazada-brand.json";

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.afterPropertiesSet();

        String key = "channel-lazada:brandcache:data:id";

//        String value = (String) redisTemplate.opsForValue().get(key);
//        AjAjTestUtils.writeFile(fileName, value);

        String brandStr = AjAjTestUtils.readFile(fileName);
        redisTemplate.opsForValue().set(key, brandStr);

    }

//    @Autowired
//    DemoService demoService;

    @Autowired(required = false)
    @Qualifier("bbbDemoService")
    DemoService bbbDemoService;
    @Autowired(required = false)
    @Qualifier("cccDemoService")
    DemoService cccDemoService;

    @Test
    void testBean() {

//        demoService.fun();
//        bbbDemoService.fun();
//        cccDemoService.fun();
        DemoService bean = (DemoService) SpringUtil.getBean(AaaDemoService.class);

        bean.fun();

    }

    @Autowired
    private MeInterceptorHandler<String, String> meInterceptorHandler;

    @Test
    void testInterceptor() throws JsonProcessingException {
        HandleResult<String> handleResult = meInterceptorHandler.preHandle("010102");
        log.info("{}", new ObjectMapper().writeValueAsString(handleResult));
    }

    @Autowired
    private  ObjectMapper objectMapper;

    @Test
    void testObjectMapper() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(new TestObject()));
    }

    @Data
    @JsonSerialize
    private static class TestObject {
    }

}
