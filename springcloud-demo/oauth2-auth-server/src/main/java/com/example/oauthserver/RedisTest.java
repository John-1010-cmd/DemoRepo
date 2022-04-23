//package com.example.oauthserver;
//
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.testng.annotations.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.annotation.Resource;
//
//
////@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void testSet() {
////        Long aLong = stringRedisTemplate.opsForList().leftPush("list", "a");
////        System.out.println(aLong);
//        this.redisTemplate.opsForValue().set("second-demo","haha");
//        System.out.println(this.redisTemplate.opsForValue().get("second-demo"));
//        this.redisTemplate.opsForValue().set("first-demo", "java redis");
//        System.out.println(this.redisTemplate.opsForValue().get("first-demo"));
//    }
//
//}
