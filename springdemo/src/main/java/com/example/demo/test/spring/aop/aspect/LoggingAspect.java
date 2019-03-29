package com.example.demo.test.spring.aop.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)//越小优先级越高
@Aspect
@Component
public class LoggingAspect {
	
	@Pointcut("execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
	public void joinpointExpress(){
		
	}
    
	@Before("joinpointExpress()")
	public void beforeMethod(JoinPoint joinPoint){
		Signature signature= joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method= methodSignature.getMethod(); 
//		Field[] fields= method.getClass().getDeclaredFields();
//		for (Field field : fields) {
//			field.getModifiers();
//		}
		System.out.println("before---log-- ==============methodName:"+method.getName());
	}
	
	@After("execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
	public void afterMethod(){
		System.out.println("after ==============");
	}
	
	@AfterReturning(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))",returning="re")
	public void returnMethod(Object re){
		System.out.println("@AfterReturning ==============result:"+re);
	}
    
	@AfterThrowing(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))",throwing="ex")
	public void returnMethod(Exception ex){
		System.out.println("@AfterThrowing ==============ex:"+ex);
	}
	
	@Around(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
	public Object around(ProceedingJoinPoint pjp){
		Object object = null;
		String methodName = pjp.getSignature().getName();

		try {
			System.err.println("around_before-----methodName:"+methodName+"--args:"+Arrays.asList(pjp.getArgs()));
			object= pjp.proceed();
			System.err.println("around_after-----methodName:"+methodName+"--args:"+Arrays.asList(pjp.getArgs()));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}
}
