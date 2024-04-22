package com.isaac.demo.dbtest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestProductRepository extends JpaRepository<TestProduct, Long> {
    Optional<TestProduct> findByName(String name);
}
