package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.dao.OrderMapper;
import com.example.demo.redis.RedisLock;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static String connectionString="127.0.0.1";

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RedisLock redisLock;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;


//    @Override
//    public void buy() {
//        //查询
//        Integer id = 1;
//
//        CuratorFramework client = ZkClientFactory.createSimple(connectionString);
//
//        InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/zjx/id_"+id);
//        try {
//            interProcessMutex.acquire();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Goods goods = orderMapper.selectByPrimaryKey(id);
//        //更新
//        goods.setStock(goods.getStock()-1);
//        orderMapper.updateByPrimaryKeySelective(goods);
//
//
//        try {
//            interProcessMutex.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }



    @Override
    public void buy() {
        //查询
        Integer id = 1;

        String lockKey = "goods_"+id;
        if(StringUtils.isEmpty(redisLock.tryLock(lockKey))){
            System.out.println("规定时间内未获取到锁");
            throw new RuntimeException("规定时间内未获取到锁");
        }
//        Goods goods = orderMapper.selectByPrimaryKey(id);
//        //更新
//        goods.setStock(goods.getStock()-1);
//        orderMapper.updateByPrimaryKeySelective(goods);
        System.out.println("程序执行中...");


        //解锁
        String lockValue = stringRedisTemplate.opsForValue().get(lockKey);
        if(redisLock.releaseLock(lockKey,lockValue)){
            System.out.println("解锁成功");
        }else{
            System.out.println("解锁失败");
        }
        System.out.println("redis值:"+lockValue);


    }
}
