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
}
