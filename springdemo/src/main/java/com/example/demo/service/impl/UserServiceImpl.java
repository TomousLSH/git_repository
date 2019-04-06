package com.example.demo.service.impl;

import com.example.demo.bean.UserBean;
import com.example.demo.dao.UserBeanMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserBeanMapper userBeanMapper;

    @Override
    public UserBean queryUserById(int id) {
        return userBeanMapper.selectByPrimaryKey(id);
    }
}
