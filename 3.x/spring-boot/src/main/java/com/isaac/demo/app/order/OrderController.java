package com.isaac.demo.app.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller + @ResponseBody = @RestController
@RestController
@Slf4j
public class OrderController {
    @GetMapping("/orders/check")
    public void healthCheck() {
        log.info("order api is healthy!!");
    }
}
