package com.example.demo.test.spring.bean;

public class Student {
	String name;
	String env;

	@Override
	public String toString() {
		return "Student [name=" + name + ", env=" + env + "]";
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
		System.err.println("set env 方法调用");

	}

	public Student() {
		System.err.println("构造方法调用");
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.name = name;
		System.err.println("set name 方法调用");
	}

	public void say() {
		System.err.println("my name is:" + name);
	}
	
	public void init(){
		System.err.println("init 方法调用");
	}
	public void destroy(){
		System.err.println("destroy 方法调用");
	}
}
