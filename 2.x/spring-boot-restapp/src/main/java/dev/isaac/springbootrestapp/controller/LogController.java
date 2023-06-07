package dev.isaac.springbootrestapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogController {    // log test
    @GetMapping("/test1")
    public String test1() {
        return "test1 : ";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2 : ";
    }
    @GetMapping("/test3")
    public String test3() {
        return "test3 : ";
    }

}
