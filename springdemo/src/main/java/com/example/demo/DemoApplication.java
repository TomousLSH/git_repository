package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 类注释
 * @author Administrator
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class DemoApplication {

	/**com.example.demo.DemoApplication
	 * 添加注释
	 * @param args
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}
	
	//tijiao
}
