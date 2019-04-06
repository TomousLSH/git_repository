package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *  @author: lsh
 *  @Date: 2019/3/31 0031
 *  @Description:
 */ 
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	UserService userService;

	@RequestMapping("/test1")
	public String test(HttpServletRequest request){

		System.out.println(JSON.toJSONString(request.getParameterMap()));
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		UserBean userBean= userService.queryUserById(Integer.valueOf(id));
		return JSON.toJSONString(userBean);
	}


}
