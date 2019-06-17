package com.example.demo.redis;

import com.example.demo.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class RedisLock {

    private Integer acquireTimeout = 1000;//尝试获取锁的时限 ms
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


    public String tryLock(String lockKey) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        String lockValue = "lock";



        Long endTime = System.currentTimeMillis() + acquireTimeout;


        while (System.currentTimeMillis() < endTime) {

            Long lockResult = (Long) redisTemplate.execute(acquireLockWithTimeout, Collections.singletonList(lockKey), lockValue, lockTime);

            //Object execute = redisTemplate.execute(acquireLockWithTimeout, stringRedisSerializer, stringRedisSerializer, Collections.singletonList(lockKey), lockValue, lockTime + "");
            //Long a = (Long) execute;
            if (lockResult.equals(1L)) {
                return lockValue+"";
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

        System.out.println("p1:"+lockKey);
        System.out.println("p2:"+lockValue);
        lockValue = "\"lock\"";
        System.out.println("p2:"+lockValue);
        Long releaseResult = (Long) redisTemplate.execute(releaseLock, Collections.singletonList(lockKey), lockValue);
        System.out.println("releaseResult:"+releaseResult);
        if (releaseResult.equals(1)) {
            return true;
        }
        return false;
    }

}
