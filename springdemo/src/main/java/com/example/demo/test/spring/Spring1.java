package com.example.demo.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.test.spring.bean.Student;


public class Spring1 {
   public static void main(String[] args) throws InterruptedException {
	   
		   ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/example/demo/test/spring/spring-config.xml");
		   Student student= (Student) context.getBean("student");
		   System.err.println("开始使用bean： "+student);
	      
		   //--
		   Thread.sleep(1000);
		   
	       ClassPathXmlApplicationContext ct = (ClassPathXmlApplicationContext) context;
	       ct.close();
	   
    }
}
