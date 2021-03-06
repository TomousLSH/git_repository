package com.example.demo.test.spring.aop.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.test.spring.aop.service.Caculator;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MathCaculator implements Caculator {

	@Override
	public int add(int a, int b) {
		int result = a+b;
		return result;
	}

	@Override
	public int sub(int a, int b) {
		int result = a-b;
		return result;
	}

	@Override
	public int mul(int a, int b) {
		int result = a*b;
		return result;
	}

	@Override
	@Transactional()
	public int div(int a, int b) {
		int result = a/b;
		return result;
	}

}
