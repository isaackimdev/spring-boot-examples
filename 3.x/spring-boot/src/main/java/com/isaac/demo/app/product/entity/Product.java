package com.isaac.demo.app.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private long price;
    private String desc;
    private long stock;
    private String category;

    // 보통 Entity에는 비즈니스 로직을 작성하지는 않는데, 이렇게 작성하는 경우도 존재한다.
    public void increase(long quantity) {
        this.stock += quantity;
    }

    public void decrease(long quantity) {
        long calculatedStock = stock - quantity;

        if (calculatedStock < 0) {
            throw new RuntimeException("Need more stock");
        }

        this.stock = calculatedStock;
    }

}
