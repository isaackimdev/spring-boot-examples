package com.isaac.springweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExamController {
    @RequestMapping("/hello-rest")
    public String hello() {
        return "Hello world!";
    }

}
