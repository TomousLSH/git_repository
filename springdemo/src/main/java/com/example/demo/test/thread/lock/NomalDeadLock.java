package com.example.demo.test.thread.lock;

import com.example.demo.util.SleepUtil;

public class NomalDeadLock {

	private static String firstLock = "a";
	private static String secondLock = "b";
	
	public static void firstToSecond(){
		String name = Thread.currentThread().getName();
		synchronized (firstLock) {
			System.out.println(name+":取得 1 锁");
			
		}
		System.out.println(name+":释放 1 锁");
		SleepUtil.sleep(1000);
		synchronized (secondLock) {
			System.out.println(name+":取得 2 锁");
		}
		System.out.println(name+":释放 2 锁");
		
	}
	
//	public static void secondToFirst(){
//		String name = Thread.currentThread().getName();
//		synchronized (secondLock) {
//			System.out.println(name+":取得 2 锁");
//			SleepUtil.sleep(1000);
//			synchronized (firstLock) {
//				System.out.println(name+":取得 1锁");
//			}
//		}
//	}
	
	public static void secondToFirst(){
		String name = Thread.currentThread().getName();
		synchronized (firstLock) {
			System.out.println(name+":取得 1 锁");
			SleepUtil.sleep(10);
			synchronized (secondLock) {
				System.out.println(name+":取得 2 锁");
			}
		}
	}
	
	
	private static class UseThread implements Runnable{

		@Override
		public void run() {
			secondToFirst();
		}
		
	} 
	
	public static void main(String[] args) {
		System.out.println("开始");
		
		Thread.currentThread().setName("主线程");
		new Thread(new UseThread(),"子线程").start();
		firstToSecond();
		
		
		
		
		System.out.println("结束");
	}
}
