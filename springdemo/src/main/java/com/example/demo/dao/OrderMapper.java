package com.example.demo.dao;

import com.example.demo.bean.Goods;
import com.example.demo.bean.UserBean;

public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}