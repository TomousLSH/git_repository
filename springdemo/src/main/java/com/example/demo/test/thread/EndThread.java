package com.example.demo.test.thread;

public class EndThread {
	
	private static class UseThread extends Thread{

		@Override
		public void run() {
			super.run();
			while(!isInterrupted()){
				System.out.println("未停止线程");
			}
			System.out.println("已经停止");
		}
		
	}
	
    public static void main(String[] args) {
    	UseThread useThread = new UseThread();
    	useThread.start();
    	
    	System.out.println("1111111111111");
    	useThread.interrupt();
    	System.out.println("2222222222222");
    	
    	
	}
}
