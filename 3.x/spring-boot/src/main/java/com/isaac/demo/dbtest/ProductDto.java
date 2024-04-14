package com.isaac.demo.dbtest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDto {
    private String name;
    private long price;

    // static factory method
    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
