package com.isaac.demo.dbtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductAController {
    private final ProductService productService;

    @GetMapping("/products/{name}")
    @ResponseBody
    public ProductDto getProduct(@PathVariable("name") String name) {
        return productService.getProduct(name);
    }

    @PostMapping("/products")
    @ResponseBody
    public ProductDto postProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto.getName(), productDto.getPrice());
    }


}
