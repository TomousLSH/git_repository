package com.example.demo.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * 初始化线程的几种方式
 */
public class InitThread {
	
	private static class UseThread extends Thread{

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+": use Thread");
		}
		
	}
	
	private static class UseRunnable implements Runnable{

		@Override
		public void run() {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName()+": use Runnable");
		}
		
	}
	
	private static class UseCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "hello";
		}
		
	}
	
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//		UseThread useThread = new UseThread();
//		useThread.start();
//		
//		
//		UseRunnable useRunnable = new UseRunnable();
//		new Thread(useRunnable).start();
		
		UseCallable useCallable = new UseCallable();
		FutureTask<String> futureTask = new FutureTask<>(useCallable);
		new Thread(futureTask).start();
		System.out.println("获取FutureTask结果："+futureTask.get());
		
		
		System.err.println("结束标志");
	}
}
