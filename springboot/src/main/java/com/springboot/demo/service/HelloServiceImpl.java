package com.springboot.demo.service;

import com.springboot.demo.mapper.HelloMapper;
import com.springboot.demo.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public Integer getCount() {
        return helloMapper.getCount();
    }
}
