package com.isaac.demo.dbtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    TestProductRepository repository;

    @Test
    void testSaveProduct() {
        TestProduct product = new TestProduct();
        product.setName("spring lecture");
        product.setPrice(1000000);

        repository.save(product);

        Optional<TestProduct> findProduct = repository.findByName("spring lecture");

        System.out.println("findProduct : " + findProduct.get().getName());
    }
}
