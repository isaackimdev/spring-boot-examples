package dev.isaac.springbootjwt.controller;

import dev.isaac.springbootjwt.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final SecurityService securityService;

    @GetMapping(value = "/")
    public String index() {
        return "Hello world";
    }

    @GetMapping(value = "/generate-token")
    public String createToken(@RequestParam("subject") String subject) {
        return securityService.createToken(subject, 1000 * 60 * 60); // 1 hour
    }

    @GetMapping(value = "/get-subject")
    public String getSubject(@RequestParam("token") String token) {
        return securityService.getSubject(token);
    }
}
