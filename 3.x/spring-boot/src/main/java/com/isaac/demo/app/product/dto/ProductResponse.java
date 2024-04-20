package com.isaac.demo.app.product.dto;

import com.isaac.demo.app.product.entity.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductResponse {
    private String name;
    private long price;
    private String desc;
    private long stock;
    private String category;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .desc(product.getDesc())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setDesc(this.desc);
        product.setStock(this.stock);
        product.setCategory(this.category);
        return product;
    }
}
