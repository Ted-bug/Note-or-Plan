package com.springboot.demo.controller;

import com.springboot.demo.pojo.Test;
import com.springboot.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello,I'm the first";
    }


    @RequestMapping("/getCount")
    public Integer getCount() {
        return helloService.getCount();
    }
}
