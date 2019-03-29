package com.example.demo.test.spring.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class ValidationAspect {
    
//	@Before("execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
//	public void beforeMethod(JoinPoint joinPoint){
//		System.out.println("before---validation ==============");
//	}
	
	@Before("com.example.demo.test.spring.aop.aspect.LoggingAspect.joinpointExpress()")
	public void beforeMethod(JoinPoint joinPoint){
		System.out.println("before---validation ==============");
	}
	
//	@After("execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
//	public void afterMethod(){
//		System.out.println("after ==============");
//	}
//	
//	@AfterReturning(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))",returning="re")
//	public void returnMethod(Object re){
//		System.out.println("@AfterReturning ==============result:"+re);
//	}
//    
//	@AfterThrowing(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))",throwing="ex")
//	public void returnMethod(Exception ex){
//		System.out.println("@AfterThrowing ==============ex:"+ex);
//	}
//	
//	@Around(value="execution(* com.example.demo.test.spring.aop.service.Caculator.*(..))")
//	public Object around(ProceedingJoinPoint pjp){
//		Object object = null;
//		String methodName = pjp.getSignature().getName();
//
//		try {
//			System.err.println("around_before-----methodName:"+methodName+"--args:"+Arrays.asList(pjp.getArgs()));
//			object= pjp.proceed();
//			System.err.println("around_after-----methodName:"+methodName+"--args:"+Arrays.asList(pjp.getArgs()));
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return object;
//	}
}
