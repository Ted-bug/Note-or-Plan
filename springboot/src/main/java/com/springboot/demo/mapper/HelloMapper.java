package com.springboot.demo.mapper;

import com.springboot.demo.pojo.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloMapper {
    Integer getCount();
}
