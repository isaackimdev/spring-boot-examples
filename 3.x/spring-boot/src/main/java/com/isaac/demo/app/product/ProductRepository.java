package com.isaac.demo.app.product;

import com.isaac.demo.app.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id= :id")
    Optional<Product> findByIdWithPessimisticLock(Long id);

    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select p from Product p where p.id= :id")
    Optional<Product> findByIdWithOptimisticLock(Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update Product p set p.stock = p.stock - :count where p.id = :productId")
    void decrease(long count, Long productId);



}
