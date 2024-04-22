package com.isaac.demo.dbtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestProductAController {
    private final TestProductService productService;

    @GetMapping("/products/{name}")
    @ResponseBody
    public TestProductDto getProduct(@PathVariable("name") String name) {
        return productService.getProduct(name);
    }

    @PostMapping("/products")
    @ResponseBody
    public TestProductDto postProduct(@RequestBody TestProductDto productDto) {
        return productService.saveProduct(productDto.getName(), productDto.getPrice());
    }


}
