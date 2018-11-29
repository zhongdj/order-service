package com.dmall.orderservice.adapter.apis;


import com.dmall.orderservice.application.OrderApplicationService;
import com.dmall.orderservice.domain.model.Order;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @Autowired
    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderApplicationService.createOrder(request.productId, request.quantity, request.totalPrice, request.address, request.phoneNumber);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") String orderId) {
        return new Order(orderId, 1, 1, new BigDecimal("1"), "address", "110", false);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Order updateOrder(@PathVariable String orderId) {
        return new Order(orderId, 9, 1, new BigDecimal("1"), "address", "110", false);
    }

    @Setter
    private static class CreateOrderRequest {
        private long productId;
        private int quantity;
        private BigDecimal totalPrice;
        private String address;
        private String phoneNumber;
    }
}
