package com.example.demo.test.singleton;
/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:
 *
 *  代码段加锁
 *  问题：线程1执行完加锁代码块之后，线程2(在加锁代码之前等待的线程，已经进入了18行判断)获得资源，执行加锁代码，但是又创建出来了一个对象
 *
 *  note:锁方法的话，效率问题
 */
public class Singleton2 {
    private Singleton2() {}

    private static Singleton2 singleton  = null;

    public static Singleton2 getInstance(){
        if(null==singleton){
            //加锁
            synchronized (Singleton2.class){
                singleton = new Singleton2();
            }
        }
        return singleton;
    }
}
