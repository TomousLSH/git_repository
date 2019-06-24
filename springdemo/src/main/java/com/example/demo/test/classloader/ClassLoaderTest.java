package com.example.demo.test.classloader;

import com.alibaba.fastjson.JSON;

public class ClassLoaderTest {
    private String s = "aaa";

    public static void main(String[] args){
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();

        int i = 1;
        while (null!=classLoader){
            System.out.println("第"+i+"个类加载器:"+classLoader.toString());
            classLoader = classLoader.getParent();
            i++;
        }

    }




    public void say(){
        System.out.println("class--say");
    }
}
