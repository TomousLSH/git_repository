package com.example.demo.test.singleton;
/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:
 *
 *  最终方案：添加volatile关键字
 *
 *  volatile保证内存的可见性，不保证操作的原子性
 *
 *  计算机硬件架构
 *
 *
 *   CPU1                                CPU2
 *   寄存器                              寄存器
 *   cache                               cache
 *
 *                 主内存
 *
 *
 *
 *  即多线程下，各个线程使用变量时，直接去主内存取数据，操作完成之后直接写回主存(不再写入寄存器或者缓存)，
 *  无 volatile时，CPU1操作完变量，不会直接写入主内存，将写入缓存，待积累一定数量缓存之后，一并写回主内存，
 *                 出现问题：线程1对变量的操作对线程2不可见
 *  有volatile ,线程2取得的对象，一定是线程1操作完成的
 *
 */
public class Singleton4 {
    private Singleton4() {}

    //添加volatile
    private static volatile Singleton4 singleton  = null;

    public static Singleton4 getInstance(){
        if(null==singleton){
            //加锁
            synchronized (Singleton4.class){
                //双重检查
                if(null==singleton){
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
