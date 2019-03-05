package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Greet;

@RestController
public class GreetController {
	
	private static final String templete = "Hello,%s";
	private final AtomicLong atomicLong = new AtomicLong();

	@RequestMapping("/greeting")
	public Greet greeting(@RequestParam(value="name",defaultValue="Word") String name){
		Greet greet = new Greet();
		greet.setId(atomicLong.incrementAndGet());
		greet.setContent(String.format(templete, name));
		return greet;
	}
}
