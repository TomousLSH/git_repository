package com.example.demo.test.singleton;
/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:
 *
 *  加锁代码段，双重检查
 *
 *  14行定义的静态变量位于方法区，
 *  jvm优化加速，多线程下会将拷贝变量到自己的工作内存，处理完之后写回主内存
 *
 *  问题：多线程下，各个线程都去拷贝17行的变量，
 *        -->各个线程21行判断时singleton都为null
 *        -->某一个线程执行完加锁代码，创建对象，并写回主内存
 *        -->其他线程执行加锁代码段，2次检查时仍然判断singleton为null(原因：各个线程拷贝了singleton，与主内存并不同步)
 *        -->于是其他线程仍然创建对象，并写回主内存
 *
 */
public class Singleton3 {
    private Singleton3() {}

    private static Singleton3 singleton  = null;

    public static Singleton3 getInstance(){
        if(null==singleton){
            //加锁
            synchronized (Singleton3.class){
                //双重检查
                if(null==singleton){
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        String s = "7B31A04BA9EC15AB10996012059A053F9156479F632415B74699F7C176DE77B52F7A9AA077B6B0F67D64FAA64A45B23D31B31F565BE46EB4CAB70E44B424DD40834293A43722834F26D3A837015A1EB0E9ABEC3CE579F152446F11E4EDD4B73418F2A1D86143AB0F9960C4DE3D4ECC6BF275477AD8433E858C92A9FC4F2ED57D";
        System.out.println(s.length());



    }
}
