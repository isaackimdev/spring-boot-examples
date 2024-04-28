package com.isaac.demo.concurrency;

import com.isaac.demo.app.order.OrderRepository;
import com.isaac.demo.app.order.OrderService;
import com.isaac.demo.app.order.dto.OrderRequest;
import com.isaac.demo.app.product.ProductRepository;
import com.isaac.demo.app.product.entity.Product;
import com.isaac.demo.app.user.UserRepository;
import com.isaac.demo.app.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@SpringBootTest
public class ConcurrencyTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setId(1L);
        user.setName("kim");
        user.setEmail("kim@test.com");
        user.setPhoneNumber("0101234567");
        user.setEncPassword("12345");

        Product product = new Product();
        product.setId(1L);
        product.setName("spring book");
        product.setDesc("this is book");
        product.setCategory("book");
        product.setPrice(15000);
        product.setStock(100);

        userRepository.save(user);
        productRepository.save(product);

    }

    @AfterEach
    public void clear() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }

    // 스레드를 이용해 여러 건의 주문을 동시에 날려볼 것임
    @Test
    public void test50StockDecrease() throws InterruptedException{
        int tryCount = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(tryCount);
        CyclicBarrier barrier = new CyclicBarrier(tryCount);
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i<tryCount; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    barrier.await();
                    OrderRequest orderRequest = OrderRequest.builder().userId(1L).productId(1L).count(1).build();
                    Long order = orderService.createOrder(orderRequest);
                    return order;
                } catch (Exception e) {
                    return null;
                }
            }));
        }
        for(Future<Long> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        Optional<Product> product = productRepository.findById(1L);
        long remainStock = product.get().getStock();
        Assertions.assertEquals(50, remainStock);
    }


}
