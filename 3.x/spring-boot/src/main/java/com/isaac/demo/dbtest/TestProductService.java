package com.isaac.demo.dbtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestProductService {
    private final TestProductRepository productRepository;

    // 선언적인 방식

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProductPrice(Long productId, long newPrice) {
        Optional<TestProduct> product = productRepository.findById(productId);
        product.get().setPrice(newPrice);
        productRepository.save(product.get());
    }

    public TestProductDto getProduct(String name) {
        Optional<TestProduct> product = productRepository.findByName(name);
        return TestProductDto.from(product.get());
    }

    public TestProductDto saveProduct(String name, long price) {
        TestProduct product = new TestProduct();
        product.setName(name);
        product.setPrice(price);

        productRepository.save(product);

        return TestProductDto.from(product);
    }


    /*
    // 프로그래밍적인 방식
    private final PlatformTransactionManager transactionManager;
    public void updateProductPrice1(Long productId, long newPrice) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            Optional<Product> product = productRepository.findById(productId);
            product.get().setPrice(newPrice);
            productRepository.save(product.get());

            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }
    }
    */

}
