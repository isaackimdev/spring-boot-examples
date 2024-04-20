package com.isaac.demo.app.product.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductRequest {
    private String name;
    private long price;
    private String desc;
    private long stock;
    private String category;
}
