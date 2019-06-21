package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // 以下两种redisTemplate自由根据场景选择
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(@Qualifier("lettuceSentineConnectionFactory") RedisConnectionFactory connectionFactory) {
        System.out.println("redis初始化:");
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) connectionFactory;
        RedisStandaloneConfiguration standaloneConfiguration = lettuceConnectionFactory.getStandaloneConfiguration();
        System.out.println("配置信息:"+JSON.toJSONString(standaloneConfiguration));

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);


        FastJsonRedisSerializer<Object> objectFastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        template.setValueSerializer(objectFastJsonRedisSerializer);
////        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }


    /**
     * Lettuce
     */
//    @Primary
//    @Bean("lettuceStandAloneConnectionFactory")
//    public RedisConnectionFactory lettuceStandAloneConnectionFactory() {
//
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
//        standaloneConfiguration.setDatabase(0);
//        standaloneConfiguration.setHostName("localhost");
//        standaloneConfiguration.setPort(6378);
//
//        return new LettuceConnectionFactory(standaloneConfiguration);
//    }

    /**
     * Lettuce
     */
    @Bean("lettuceSentineConnectionFactory")
    public RedisConnectionFactory lettuceSentineConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("10.237.22.190", 9379)
                .sentinel("10.237.22.192", 9380);


        return new LettuceConnectionFactory(sentinelConfig);
    }

}
