package dev.isaac.springbootrestapp.controller;

import dev.isaac.springbootrestapp.annotation.TokenRequired;
import dev.isaac.springbootrestapp.service.JwtService;
import dev.isaac.springbootrestapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping("hello")
    public Map<String, String> hello() {
        Map<String, String> res = userService.getMessage();
        return res;
    }

    @TokenRequired
    @GetMapping("")
    public String index() {
        return "Hello world..";
    }

    @GetMapping("/security/generate/token")
    public Map<String, Object> generateToken(@RequestParam(value = "subject") String subject) {
        String token = jwtService.createToken(subject, 1000 * 60 * 60 * 24);
        Map<String, Object> map = new HashMap<>();
        map.put("subject", subject);
        map.put("token", token);
        return map;
    }

    @GetMapping("/security/get/subject")
    public String getSubject(@RequestParam("token") String token) {
        return jwtService.getSubject(token);
    }
}
