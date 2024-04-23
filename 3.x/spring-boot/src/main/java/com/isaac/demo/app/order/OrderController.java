package com.isaac.demo.app.order;

import com.isaac.demo.app.order.dto.OrderRequest;
import com.isaac.demo.app.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @Controller + @ResponseBody = @RestController
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders/check")
    public void healthCheck() {
        log.info("order api is healthy!!");
    }

    @PostMapping("/api/orders")
    public ResponseEntity<Map<String, Long>> postOrder(@RequestBody OrderRequest request) {
        Long orderid = orderService.createOrder(request);
        Map<String, Long> response = new HashMap<>();
        response.put("orderId", orderid);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/orders/{id}/cancel")
    public ResponseEntity<Map<String, Long>> putOrder(@PathVariable("id") Long id) {
        Long orderid = orderService.cancelOrder(id);
        Map<String, Long> response = new HashMap<>();
        response.put("orderId", orderid);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
        OrderResponse order = orderService.getOrderDetails(id);
        return ResponseEntity.ok().body(order);
    }

}
