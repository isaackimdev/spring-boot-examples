package com.isaac.springbootthymeleaf.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello"; // "hello", "/hello", "hello.html"
    }

    @RequestMapping("/data-test")
    public String dataTest(Model model) { // Model 선언
        // Data 세팅
        model.addAttribute("data1", "대한민국");
        return "data-test";
    }
}
