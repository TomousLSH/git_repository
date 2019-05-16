package com.example.demo.test.jvm;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -5078430378850292287L;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
