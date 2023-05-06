package com.isaac.springbootmybatis.controller;

import com.isaac.springbootmybatis.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestMapper testMapper;

    @GetMapping("/")
    public String hello() {
        return String.valueOf(testMapper.totSize("test"));
    }


    /**
     * CRUD Test
     * */




}
