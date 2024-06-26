package com.isaac.demo.dbtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @MockBean
    TestProductRepository productRepository;  // 가짜 객체
    @Autowired
    TestProductService productService;

    @Test
    public void getProductTest() {
        TestProduct product = new TestProduct();
        product.setName("book");
        product.setPrice(10000);

        when(productRepository.findByName(product.getName())).thenReturn(Optional.of(product));

        TestProductDto productDto = productService.getProduct(product.getName());

        assertEquals("book", productDto.getName());
        verify(productRepository).findByName(product.getName());
    }
    @Test
    public void saveProductTest() {
        TestProduct product = new TestProduct();
        product.setName("book");
        product.setPrice(10000);
        when(productRepository.save(product)).thenReturn(product);

        TestProductDto productDto = productService.saveProduct(product.getName(), product.getPrice());

        assertEquals("book", productDto.getName());

    }

}