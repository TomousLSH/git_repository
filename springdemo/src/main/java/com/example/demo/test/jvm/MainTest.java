package com.example.demo.test.jvm;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {
    public static void main(String[] args){

        Person p1 =new Person("libai");

        Person p2 = new Person();
        p2 = p1;

        //p1.setName("dufu");
        p2.setName("dufu");

        System.out.println(p1);
        System.out.println(p2);


        LinkedList<String> linkedList = new LinkedList<>();
        String s = "my";
        for(int i = 0; i < 30; i++ ){
            linkedList.add(s+i);
        }

        new HashMap<>();

        String re = linkedList.get(20);
        new ArrayList<>();

        AtomicInteger atomicInteger= new AtomicInteger(6);
        atomicInteger.incrementAndGet();
        Set ss = new HashSet();
        ss.add(1);
        ss.add(2);
        ss.add(3);
        ss.add(1);
        System.out.println(ss.size());
        //https://mirrors.cnnic.cn/jenkins/plugins/script-security/1.56/script-security.hpi
        //http://updates.jenkins-ci.org/download/plugins/script-security/1.56/script-security.hpi

        //https://mirrors.tuna.tsinghua.edu.cn/jenkins/updates/update-center.json

        // http://mirror.xmission.com/jenkins/updates/update-center.json
         // http://ftp.tsukuba.wide.ad.jp/software/jenkins/updates/current/update-center.json
        // http://mirror.esuni.jp/jenkins/updates/update-center.json


        System.out.println("---------------------------------");
        System.out.println(13>>1);
        System.out.println(14>>1);
        System.out.println(15>>1);
        System.out.println(16>>1);
        System.out.println(17>>1);
        System.out.println(18>>1);
        System.out.println(19>>1);
        System.out.println(30>>1);
    }
}
