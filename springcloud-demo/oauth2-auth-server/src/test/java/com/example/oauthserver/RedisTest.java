package com.example.oauthserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testSet() {
//        this.redisTemplate.opsForValue().set("third-demo","haha");
//        System.out.println(this.redisTemplate.opsForValue().get("third-demo"));
        this.redisTemplate.opsForValue().set("first-demo", "java redis");
        System.out.println(this.redisTemplate.opsForValue().get("first-demo"));
        this.redisTemplate.opsForValue().set("second-demo", "张三");
        System.out.println(this.redisTemplate.opsForValue().get("second-demo"));
    }
}
