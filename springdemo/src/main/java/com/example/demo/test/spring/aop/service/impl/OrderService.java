package com.example.demo.test.spring.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.test.spring.aop.service.Caculator;

@Service
public class OrderService {
	
   @Autowired
   Caculator caculator;
	
   public int calcuPrice(){
	   System.out.println(caculator);
	    System.out.println("计算价格");
	    return caculator.add(41, 11);
   }
}
