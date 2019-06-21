package com.example.demo.redis;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisChangeDbUtil {

    public static void changeDb(RedisTemplate redisTemplate, int index){
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        lettuceConnectionFactory.setDatabase(index);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        lettuceConnectionFactory.resetConnection();
    }
}
