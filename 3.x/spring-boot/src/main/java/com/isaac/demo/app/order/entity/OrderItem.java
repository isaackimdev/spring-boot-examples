package com.isaac.demo.app.order.entity;

import com.isaac.demo.app.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 실무에서 양방향 관계를 잘 쓰지는 않는다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private long orderPrice;
    private long count;

    public static OrderItem createOrderItem(Product product, long orderPrice, long count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        // product.decrease(count);
        return orderItem;
    }

    public void cancel() {
        getProduct().increase(this.count);
    }
    public long getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}
