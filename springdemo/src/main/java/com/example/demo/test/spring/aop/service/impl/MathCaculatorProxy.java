package com.example.demo.test.spring.aop.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.stereotype.Service;

@Service
public class MathCaculatorProxy {
	
    Object target;
    
    
    public Object getTarget() {
		return target;
	}


	public void setTarget(Object target) {
		this.target = target;
	}


	public Object getInstanceProxy(){
    	
    	ClassLoader loader = target.getClass().getClassLoader();
    	Class<?>[] interfaces = target.getClass().getInterfaces();
    	InvocationHandler h = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("前置增强");
				Object obj= method.invoke(target , args);
				System.out.println("后置增强");
				return obj;
			}
		};
    	return Proxy.newProxyInstance(loader, interfaces, h);
    }
}
