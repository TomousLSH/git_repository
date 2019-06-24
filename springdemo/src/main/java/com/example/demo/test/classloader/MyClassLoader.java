package com.example.demo.test.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{

    private static final String WEB_ROOT = "D:\\zzlib";

    /**
     * 一般自己定义classloader 只需要实现findclass即可
     * @param name 全包名
     * @return Class
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        System.out.println("数据长度:"+classData.length);
        if (classData == null || classData.length <= 0) {
            throw new ClassNotFoundException();
        }
        String[] strings = name.split("\\.");
        return defineClass(strings[strings.length-1], classData, 0, classData.length);
    }
    /**
     * 使用流读取指定目录下的class文件
     * @param name
     * @return
     */
    public byte[] getClassData(String name) {
        String path = getClassPath(name);
        System.out.println("path:"+path);
        byte[] container = new byte[4096];
        try {
            int i;
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((i = inputStream.read(container)) != -1) {
                out.write(container,0,i);
            }
            return out.toByteArray();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取class文件所在路径
     * @param name
     * @return
     */
    private String getClassPath(String name) {
        return WEB_ROOT + File.separator + name.replace(".", File.separator) + ".class";
        //return "/Hello.class";
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> helloClass = myClassLoader.loadClass("com.test.Hello");
        System.out.println(helloClass.getClassLoader());

        Object o = helloClass.newInstance();
        Method say = helloClass.getMethod("say", null);
        say.invoke(o, null);

        System.out.println(o);
    }

}
