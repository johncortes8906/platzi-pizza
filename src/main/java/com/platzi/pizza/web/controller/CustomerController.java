package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.service.CustomerService;
import com.platzi.pizza.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService,
                              OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> findAll() {
        return ResponseEntity.ok(
            this.customerService.findAll()
        );
    }

    @GetMapping("/phone={phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(
                this.customerService.findByPhone(phone)
        );
    }

    @GetMapping("/customer_id={customerId}")
    public ResponseEntity<List<OrderEntity>> findByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(
                this.orderService.findByCustomerId(customerId)
        );
    }
}
