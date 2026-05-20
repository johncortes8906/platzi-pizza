package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projections.OrderSummary;
import com.platzi.pizza.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getOrders() {
        return ResponseEntity.ok(
                this.orderService.getAll()
        );
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(
                this.orderService.getTodayOrders()
        );
    }

    @GetMapping("/methods")
    public  ResponseEntity<List<OrderEntity>> getOrdersByMethod() {
        return ResponseEntity.ok(
                this.orderService.getOrdersByMethod()
        );
    }

    @GetMapping("/customer_id={customerId}")
    public ResponseEntity<List<OrderEntity>> findByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(
                this.orderService.findByCustomerId(customerId)
        );
    }

    @GetMapping("/summary/order_id={orderId}")
    public ResponseEntity<OrderSummary> getOrderSummaryByOrderId(@PathVariable int orderId) {
        return ResponseEntity.ok(
                this.orderService.findOrderSummaryByOrderId(orderId)
        );
    }
}
