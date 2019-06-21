package com.example.demo.service;

import com.example.demo.bean.UserBean;

public interface OrderService {
    void buy();

    void testPipeline(int num);

    void testNoPipeline();

    void redistransaction();

    public void testSentinel();

}
