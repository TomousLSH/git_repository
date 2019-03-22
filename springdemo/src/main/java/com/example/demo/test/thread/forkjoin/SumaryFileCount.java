package com.example.demo.test.thread.forkjoin;

import java.io.File;

public class SumaryFileCount {
	private static int directorCount = 0;
	private static int fileCount = 0;
	
	private static String FILE_PATH= "G:/zxzc-his";
	//private static String FILE_PATH= "G:/zxzc-his/归档-zx/linux/empty/dsd.txt";
    public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
        
    	sumary(FILE_PATH);
    	Thread.sleep(1000);
    	System.out.println("时间:"+(System.currentTimeMillis()-start));
    	
    	System.out.println("统计结果，文件夹个数:"+directorCount);
    	System.out.println("统计结果，文件个数:"+fileCount);
	}
    
    
    private static void sumary(String filePath){
    	File file = new File(filePath);

    	File[] list = file.listFiles();//返回结果有3个     1.单独文件返回null     2.空文件夹返回【】，空数组       3.正常有长度数组 
    	if (null==list) {
    		System.out.println("文件名字："+file.getName());
			fileCount++;
			return;
		}
    	
    	if (list.length==0) {
    		return;
		}
    	
		for (File file2 : list) {
			if (file2.isDirectory()) {
				directorCount++;
				sumary(file2.getAbsolutePath());
			} else {
				System.out.println("文件名字：" + file2.getName());
				fileCount++;
			}
		}

    }
}
