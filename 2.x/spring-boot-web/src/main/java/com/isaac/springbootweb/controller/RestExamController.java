package com.isaac.springbootweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestExamController {
    @RequestMapping("/hello-rest")
    public String hello() {
        return "Hello world!";
    }

    @RequestMapping("/json")
    public Map<String, Object> json1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","isaac");
        map.put("age", 20);
        map.put("like alphabet", 'S');
        return map;
    }
}
