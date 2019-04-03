package com.example.demo.test.thread.forkjoin;

import java.util.concurrent.CountDownLatch;

import com.example.demo.util.SleepUtil;

public class CountDownlatchTest {
	private static Integer count = 30;
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	
	private static class UseRunnable implements Runnable{
		private static CountDownLatch ct;
		public UseRunnable(CountDownLatch ct) {
			super();
			UseRunnable.ct = ct;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println(Thread.currentThread().getName()+"wait");
				ct.await();
				System.out.println(Thread.currentThread().getName()+"开始执行");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
    public static void main(String[] args) {
		
    	for (int i = 0; i < count; i++) {
			UseRunnable useRunnable = new UseRunnable(countDownLatch);
			new Thread(useRunnable,"线程"+i+"号").start();
		}
    	SleepUtil.sleep(1000);
    	countDownLatch.countDown();
    	
    	System.out.println("count_____________________________end");
    }
}
