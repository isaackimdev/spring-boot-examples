package com.isaac.springbootthymeleaf.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello"; // "hello", "/hello", "hello.html"
    }

    // Model(data) test
    @RequestMapping("/data-test")
    public String dataTest(Model model) { // Model 선언
        // Data 세팅
        model.addAttribute("data1", "대한민국");
        return "data-test";
    }

    // ModelAndView test
    @RequestMapping("/mv-test")
    public ModelAndView modelAndViewTest(ModelAndView modelAndView) { // Model(Data) + View 지정

        // (1) 데이터 지정
        // (2) 뷰페이지 지정
        // (3) return
        modelAndView.addObject("name", "이순신");
        modelAndView.addObject("age", 29);
        modelAndView.setViewName("mv-test");
        return modelAndView;
    }
}
