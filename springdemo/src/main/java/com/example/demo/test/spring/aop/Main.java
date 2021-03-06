package com.example.demo.test.spring.aop;

import com.example.demo.test.spring.aop.service.Caculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/example/demo/test/spring/aop/spring-config.xml");
        Caculator caculator = (Caculator) context.getBean("mathCaculator");
        System.err.println(caculator);
//		   MathCaculatorProxy mathCaculatorProxy=  (MathCaculatorProxy) context.getBean("mathCaculatorProxy");
//		   mathCaculatorProxy.setTarget(caculator);
//
//		   Object obj = mathCaculatorProxy.getInstanceProxy();
//		   Caculator proxy = (Caculator)obj ;
//		   System.err.println("result:"+proxy.add(1, 2));

//		   int re= caculator.add(2, 8);
//	       System.out.println("result:"+re);

        System.out.println("result:" + caculator.div(12, 2));

        Thread.sleep(1000);

        ClassPathXmlApplicationContext ct = (ClassPathXmlApplicationContext) context;
        ct.close();
    }


}
