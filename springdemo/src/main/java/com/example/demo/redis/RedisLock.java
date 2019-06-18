package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RedisLock {

    private Integer acquireTimeout = 10000;//尝试获取锁的时限 ms
    private Integer acquireInterval = 10;//尝试获取锁的时间间隔 ms
    private Integer lockTime = 1000000;//资源占有锁的时间 秒s

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("lockScript")
    private RedisScript<Integer> acquireLockWithTimeout;
    @Autowired
    @Qualifier("unLockScript")
    private RedisScript<Integer> releaseLock;


    public String tryLock(String lockKey,String lockVal) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();



        Long endTime = System.currentTimeMillis() + acquireTimeout;


        while (System.currentTimeMillis() < endTime) {
            // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
            //Long lockResult = (Long) redisTemplate.execute(acquireLockWithTimeout, Collections.singletonList(lockKey), lockValue, lockTime);

            //lua 脚本使用这个
            Long lockResult = (Long)redisTemplate.execute(acquireLockWithTimeout, stringRedisSerializer, stringRedisSerializer, Collections.singletonList(lockKey), lockVal, lockTime + "");

            if (lockResult.equals(1L)) {
                return lockVal+"";
            } else {
                try {
                    Thread.sleep(acquireInterval);
                } catch (InterruptedException ex) {
                    continue;
                }
            }
        }
        return "";
    }

    public boolean releaseLock(String lockKey, String lockValue) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Long releaseResult = (Long) redisTemplate.execute(releaseLock,stringRedisSerializer,stringRedisSerializer, Collections.singletonList(lockKey),lockValue);
        //System.out.println("----releaseResult:"+releaseResult);
        if (releaseResult.equals(1L)) {

            return true;
        }
        return false;

    }

}
