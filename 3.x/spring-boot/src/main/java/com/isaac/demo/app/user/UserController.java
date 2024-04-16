package com.isaac.demo.app.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/users/check")
    public void healthCheck() {
        log.info("user api is healthy!!");
    }

}
