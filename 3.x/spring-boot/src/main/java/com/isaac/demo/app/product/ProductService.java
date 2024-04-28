package com.isaac.demo.app.product;

import com.isaac.demo.app.product.dto.ProductRequest;
import com.isaac.demo.app.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(long id, ProductRequest request);
    ProductResponse getProductById(long id);
    List<ProductResponse> getAllProduct();
}
