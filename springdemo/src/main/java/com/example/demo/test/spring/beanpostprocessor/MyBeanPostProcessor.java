package com.example.demo.test.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.example.demo.test.spring.bean.Student;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.err.println("postProcess---Before---Initialization:"+bean+"---:"+beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.err.println("postProcess---After---Initialization:"+bean+"---:"+beanName);
				
		return null;
	}

}
