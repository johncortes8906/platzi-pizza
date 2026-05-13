package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @Column(name = "customer_id", nullable = false, unique = true, length = 15)
    private String customerId;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(length = 100)
    private String address;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
