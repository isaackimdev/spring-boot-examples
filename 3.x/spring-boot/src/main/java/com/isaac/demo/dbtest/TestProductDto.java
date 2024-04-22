package com.isaac.demo.dbtest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TestProductDto {
    private String name;
    private long price;

    // static factory method
    public static TestProductDto from(TestProduct product) {
        return TestProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
