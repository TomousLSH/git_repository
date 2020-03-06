package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kong")
public class KongLogSentController {

    @RequestMapping(method = RequestMethod.POST,value = "/receivelog",produces = "application/json;charset=utf-8")
    public HashMap receivelog(HttpServletRequest request, @RequestBody JSONObject object){
       // System.out.println(JSON.toJSONString(request));

        System.out.println("接收到日志请求");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(JSON.toJSONString(parameterMap));

        System.out.println(JSON.toJSONString(object));


        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("name", "li");
        return hashMap;
    }

}
