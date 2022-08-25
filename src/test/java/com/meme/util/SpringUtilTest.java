package com.meme.util;

import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
//@RunWith(SpringRunner.class)
public class SpringUtilTest {
/*
    @Test
    public void testBean() {
        System.out.println(

        SpringUtil.getBean("CommonService").getClass().getName()
        );
    }
*/
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        String key = "aaaaa";

//        redisTemplate.opsForValue().set(key, "11111");
        Long l = redisTemplate.opsForValue().increment(key, 101L);
        System.out.println(l);
        Object o = redisTemplate.opsForValue().get("\\xac\\xed\\x00\\x05t\\x00\\x05aaaaa");
        System.out.println(o);
    }
}
