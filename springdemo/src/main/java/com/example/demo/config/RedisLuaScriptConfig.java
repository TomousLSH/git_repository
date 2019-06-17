package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

@Component
public class RedisLuaScriptConfig {
    @Bean
    @Qualifier("lockScript")
    public RedisScript<Integer> acquireLockWithTimeout() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("redis-lua-script/acquire_lock_with_timeout.lua"));
        redisScript.setResultType(Integer.class);
        return redisScript;
    }


    @Bean
    @Qualifier("unLockScript")
    public RedisScript<Integer> releaseLock() {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("redis-lua-script/release_lock.lua"));
        redisScript.setResultType(Integer.class);
        return redisScript;
    }
}
