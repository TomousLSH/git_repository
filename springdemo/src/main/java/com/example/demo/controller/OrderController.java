package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/center")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/buy")
    public String buy(HttpServletRequest request){
        orderService.buy();
        return "success";
    }

    @RequestMapping("/pipeline")
    public String pipeline(HttpServletRequest request){
        String a = request.getParameter("num");
        int num = Integer.parseInt(a);
        System.out.println("接收参数:"+num);
        orderService.testPipeline(num);
        return "success";
    }

    @RequestMapping("/nopipeline")
    public String nopipeline(HttpServletRequest request){

        orderService.testNoPipeline();
        return "success";
    }

    @RequestMapping("/redistransaction")
    public String redistransaction(HttpServletRequest request){

        orderService.redistransaction();
        return "success";
    }


    @RequestMapping("/testSentinel")
    public String testSentinel(HttpServletRequest request){

        orderService.testSentinel();
        return "success";
    }
}
