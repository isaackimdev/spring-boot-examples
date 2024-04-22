package com.isaac.demo.app.product;

import com.isaac.demo.app.product.dto.ProductRequest;
import com.isaac.demo.app.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/check")
    public void healthCheck() {
        log.info("product api is healthy!!");
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> postProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.createProduct(request));
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> putProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.updateProduct(id, request));
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

}
