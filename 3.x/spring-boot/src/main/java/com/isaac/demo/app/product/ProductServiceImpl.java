package com.isaac.demo.app.product;

import com.isaac.demo.app.product.dto.ProductRequest;
import com.isaac.demo.app.product.dto.ProductResponse;
import com.isaac.demo.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDesc(request.getDesc());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        productRepository.save(product);
        return ProductResponse.from(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(long id, ProductRequest request) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        findProduct.setName(request.getName());
        findProduct.setPrice(request.getPrice());
        findProduct.setDesc(request.getDesc());
        findProduct.setStock(request.getStock());
        findProduct.setCategory(request.getCategory());

        return ProductResponse.from(findProduct);
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponse.from(findProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> findProducts = productRepository.findAll();
        return findProducts.stream().map(ProductResponse::from).toList();
    }

}
