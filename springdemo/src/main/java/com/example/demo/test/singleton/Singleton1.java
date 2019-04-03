package com.example.demo.test.singleton;

/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:饱汉
 *
 *  未作任何操作：
 *  问题：多线程下，17行代码，new 出来了多个对象
 */
public class Singleton1 {
    private Singleton1() {}

    private static Singleton1 singleton  = null;

    public static Singleton1 getInstance(){
        if(null==singleton){
            singleton = new Singleton1();
        }
        return singleton;
    }
}
