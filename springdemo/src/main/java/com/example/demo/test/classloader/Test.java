package com.example.demo.test.classloader;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classloader = new MyClassLoader();
        Class<?> c1 = Class.forName("com.lsh.Hello", true, classloader);
        //Class<?> myClass = classloader.loadClass("com.lsh.Hello");
        Object o = c1.newInstance();
        System.out.println(o);
    }
}
