package com.isaac.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebMvcController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello.html";
    }
}
