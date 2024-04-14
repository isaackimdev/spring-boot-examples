package com.isaac.demo.dbtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 선언적인 방식

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProductPrice(Long productId, long newPrice) {
        Optional<Product> product = productRepository.findById(productId);
        product.get().setPrice(newPrice);
        productRepository.save(product.get());
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
