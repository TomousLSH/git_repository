package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void  testGetUser(){
        UserBean userBean = userService.queryUserById(2);
        System.out.println(JSON.toJSONString(userBean));
    }
}