package com.example.demo.test.classloader;

public class ClassLoaderTest1 {
    public static void main(String[] args){
        try {
            Class c = Class.forName("com.example.demo.test.classloader.ClassLoaderTest");
            ClassLoaderTest h = (ClassLoaderTest) c.newInstance();
            h.say();

            System.out.println(System.getProperty("user.dir"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
