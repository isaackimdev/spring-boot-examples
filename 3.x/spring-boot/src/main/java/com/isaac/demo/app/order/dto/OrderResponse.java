package com.isaac.demo.app.order.dto;

import com.isaac.demo.app.order.entity.Order;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class OrderResponse {
    private Long userId;
    private Long orderId;
    private List<OrderItemDto> orderItems;
    private String orderStatus;
    private LocalDateTime orderDate;
    private long totalPrice;

    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
                .userId(order.getUser().getId())
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderItems(
                        order.getOrderItems().stream().map(OrderItemDto::from).toList())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }

}
