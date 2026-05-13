package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "customer_id", nullable = false, length = 15)
    private String customerId;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double total;
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;
    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

}
