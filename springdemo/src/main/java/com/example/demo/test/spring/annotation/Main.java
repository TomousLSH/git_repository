package com.example.demo.test.spring.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.test.spring.annotation.service.OrderService;

public class Main {
	   public static void main(String[] args) throws InterruptedException {
		   
		   ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/example/demo/test/spring/annotation/spring-config.xml");
		   OrderService order=  (OrderService) context.getBean("orderService");
		   System.err.println("开始使用bean： "+order);
	      
		   
		   Thread.sleep(1000);
		   
	       ClassPathXmlApplicationContext ct = (ClassPathXmlApplicationContext) context;
	       ct.close();
	   
    }
}
