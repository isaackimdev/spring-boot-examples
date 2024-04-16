package com.isaac.demo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello-test")
    public String hi(@RequestParam(name="id", required = false) String id, Model model) {
        model.addAttribute("data", id);
        return "hello";
    }
}
