package com.isaac.demo.app.order;

import com.isaac.demo.app.order.dto.OrderRequest;
import com.isaac.demo.app.order.dto.OrderResponse;
import com.isaac.demo.app.order.entity.Order;
import com.isaac.demo.app.order.entity.OrderItem;
import com.isaac.demo.app.policy.UserDiscountPolicy;
import com.isaac.demo.app.product.ProductRepository;
import com.isaac.demo.app.product.entity.Product;
import com.isaac.demo.app.user.UserRepository;
import com.isaac.demo.app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserDiscountPolicy userDiscountPolicy;

    @Override
    @Transactional
    public Long createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        long discountPrice = product.getPrice() - userDiscountPolicy.discount(user, product.getPrice());

        OrderItem orderItem = OrderItem.createOrderItem(product, discountPrice, orderRequest.getCount());
        Order order = Order.from(user, orderItem);

        productRepository.decrease(orderRequest.getCount(), product.getId());
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    @Transactional
    public Long cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        order.cancel();

        return orderId;
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderResponse.from(order);
    }

}
