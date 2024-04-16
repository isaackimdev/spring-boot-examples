package com.isaac.demo.app.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @GetMapping("/products/check")
    public void healthCheck() {
        log.info("product api is healthy!!");
    }
}
