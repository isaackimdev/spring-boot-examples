package com.isaac.demo.app.order.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderRequest {
    private Long userId;
    private Long productId;
    private long count;
}
