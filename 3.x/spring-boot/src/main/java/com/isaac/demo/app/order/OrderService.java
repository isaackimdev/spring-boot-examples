package com.isaac.demo.app.order;

import com.isaac.demo.app.order.dto.OrderRequest;
import com.isaac.demo.app.order.dto.OrderResponse;

public interface OrderService {
    Long createOrder(OrderRequest orderRequest);
    Long cancelOrder(Long orderId);
    OrderResponse getOrderDetails(Long orderId);
}
