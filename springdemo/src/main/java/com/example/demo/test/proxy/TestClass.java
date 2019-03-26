package com.example.demo.test.proxy;

public class TestClass {
    public static void main(String[] args) {
		ShopProxy shopProxy = new ShopProxy();
		WawaInterface hanLaoShiWawaService = new HanLaoShiWawaService();
		
		Object o = hanLaoShiWawaService;
		System.out.println(o.getClass().getName());
		shopProxy.setObj(hanLaoShiWawaService);
		
		WawaInterface proxy = (WawaInterface) shopProxy.getProxy();
		proxy.createWawa();
	}
}
