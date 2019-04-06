package com.example.demo.dao;

import com.example.demo.bean.UserBean;

public interface UserBeanMapper {

    UserBean selectByPrimaryKey(Integer id);
}