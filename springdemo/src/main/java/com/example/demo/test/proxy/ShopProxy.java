package com.example.demo.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ShopProxy implements InvocationHandler{
	Object obj;
	
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	Object getProxy(){
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Before();
		if (proxy instanceof WawaInterface) {
			System.out.println("wwwwwwwwwwwwwwwwwww");
		}
		Object object= method.invoke(obj, args);
		end();
		return object;
	}

	private void end() {
		System.out.println("后置增强");
	}

	private void Before() {
		System.out.println("前置增强");
	}

}
