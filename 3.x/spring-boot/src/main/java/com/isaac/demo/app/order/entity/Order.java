package com.isaac.demo.app.order.entity;

import com.isaac.demo.app.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_tbl")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 관계 설정
    // EAGER, LAZY
    // EAGER : 전부 로딩
    // LAZY : 쓸 때 로딩 (실무에서 많이 씀)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 주문에 속한 많은 주문 아이템
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    private String orderStatus;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Param : List<OrderItem> orderItems
    public static Order from(User user, OrderItem... orderItems) {
        Order order = new Order();
        order.setUser(user);

        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus("ORDER");
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    public void cancel() {
        this.setOrderStatus("CANCEL");
        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
