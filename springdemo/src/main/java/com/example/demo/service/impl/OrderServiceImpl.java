package com.example.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Goods;
import com.example.demo.bean.UserBean;
import com.example.demo.dao.OrderMapper;
import com.example.demo.redis.RedisChangeDbUtil;
import com.example.demo.redis.RedisLock;
import com.example.demo.service.OrderService;
import com.example.demo.test.jvm.Person;
import com.example.demo.util.SleepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private static String connectionString="127.0.0.1";

    private static int count = 600000;

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
        UUID uuid = UUID.randomUUID();

        if(StringUtils.isEmpty(redisLock.tryLock(lockKey,uuid.toString()))){
            System.out.println("规定时间内未获取到锁");
            throw new RuntimeException("规定时间内未获取到锁");
        }
        Goods goods = orderMapper.selectByPrimaryKey(id);
        //更新
        goods.setStock(goods.getStock()-1);
        orderMapper.updateByPrimaryKeySelective(goods);
        //System.out.println("程序执行中...");


        //解锁
        //String lockValue = stringRedisTemplate.opsForValue().get(lockKey);
        if(redisLock.releaseLock(lockKey,uuid.toString())){
            System.out.println("解锁成功");
        }else{
            System.out.println("解锁失败");
        }


    }

    @Override
    public void testPipeline(int num){
        //
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        String[] s = list.toArray(new String[list.size()]);

        Long begin = System.currentTimeMillis();

        RedisChangeDbUtil.changeDb(stringRedisTemplate, 7);
//        List<Object> objects = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.openPipeline();
//                StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
//                stringRedisConnection.lPush("als",s);
//                stringRedisConnection.set("name", "lsh");
//                stringRedisConnection.set("age", "18");
//                return null;
//            }
//        });

        List<Object> objects = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
                stringRedisConnection.lRange("als",0L, -1L);
                stringRedisConnection.get("name");
                stringRedisConnection.get("age");
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("pipeline执行时间:"+(end-begin));
       System.out.println(JSON.toJSONString(objects));
    }

    @Override
    public void testNoPipeline() {
        Long begin = System.currentTimeMillis();


        for (int i = 0; i < count; i++) {

            stringRedisTemplate.opsForValue().set("key" + i, "value" + i);
        }

        Long end = System.currentTimeMillis();
        System.out.println("normal执行时间:"+(end-begin));
    }

    @Override
    public void redistransaction() {
        //切换DB
        RedisChangeDbUtil.changeDb(stringRedisTemplate, 8);

        //首先查询结果
        String s = stringRedisTemplate.opsForValue().get("count");

        SleepUtil.sleep(500);

        //每次减少1个，并存入
        int count = Integer.valueOf(s) - 1;

        stringRedisTemplate.opsForValue().set("count", count+"");

    }




    /**
     *  @author: lsh
     *  @Date: 2019/6/20 0020
     *  @Description:
     *
     *  BeanUtilsHashMapper using Spring’s BeanUtils.
     *
     *  ObjectHashMapper using Object-to-Hash Mapping.
     *
     *  Jackson2HashMapper using FasterXML Jackson.
     */
    public void testHashMapper(){
        UserBean userBean = new UserBean();
        userBean.setName("lsh");
        userBean.setId(1);
        userBean.setAge(19);
        userBean.setAddress("北京市");

        ObjectHashMapper hashMapper = new ObjectHashMapper();
        Map map = hashMapper.toHash(userBean);

        System.out.println(JSON.toJSONString(map));

    }

    @Override
    public void testSentinel(){
        RedisChangeDbUtil.changeDb(stringRedisTemplate, 1);
        String date = stringRedisTemplate.opsForValue().get("beginTime");
        System.out.println("sentinel:"+date);

        RedisChangeDbUtil.changeDb(stringRedisTemplate, 5);
        stringRedisTemplate.opsForValue().set("name", "lig");
    }


}
