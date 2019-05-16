package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.UserBean;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@RequestMapping("/test1.json")
	public String test(HttpServletRequest request){

		System.out.println(JSON.toJSONString(request.getParameterMap()));
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		UserBean userBean= userService.queryUserById(Integer.valueOf(id));
		return JSON.toJSONString(userBean);
	}

	@RequestMapping("/test2.json")
	public String test2(HttpServletRequest request){

		return "{\"ecode\":\"0\",\"emsg\":\"\",\"data\":{\"list\":[{\"id\":1,\"uname\":\"文广军---Android开发\",\"age\":\"30\",\"sex\":\"男\",\"address\":\"北京\",},{\"id\":2,\"uname\":\"罗丹---药剂师\",\"age\":\"24\",\"sex\":\"女\",\"address\":\"益阳\",}]}}";
	}

	@RequestMapping("/test3.json")
	public String test3(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		map.put("ecode", "0");
		map.put("emsg", "0");

		String username=request.getParameter("username");
		String password=request.getParameter("password");
        System.out.println("username:"+username);
        System.out.println("password:"+password);

		Map<String, Object> map1 = new HashMap<>();

		List<Map> list = new ArrayList<>();
		Map<String, Object> wgjMap = new HashMap<>();
		wgjMap.put("id", 1);
		wgjMap.put("username", username);
		wgjMap.put("password", password);
		wgjMap.put("age", "30");
		wgjMap.put("sex", "男");
		wgjMap.put("address", "北京");

//		Map<String, Object> ldMap = new HashMap<>();
//		ldMap.put("id", 2);
//		ldMap.put("uname", "罗丹---药剂师");
//		ldMap.put("age", "24");
//		ldMap.put("sex", "女");
//		ldMap.put("address", "益阳");
		list.add(wgjMap);
		//list.add(ldMap);

		Map<String, Object> innerMap = new HashMap<>();
		innerMap.put("list", list);
		map.put("data", innerMap);
		return JSON.toJSONString(map);
	}
}
