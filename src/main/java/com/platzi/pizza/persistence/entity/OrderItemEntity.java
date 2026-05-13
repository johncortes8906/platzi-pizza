package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.geom.GeneralPath;

@Entity
@Table(name = "orders_by_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "pizza_id", nullable = false)
    private Integer pizzaId;
    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double quantity;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @OneToOne
    @JoinColumn(name = "pizza_id", referencedColumnName = "pizza_id", insertable = false, updatable = false)
    private PizzaEntity pizza;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    private OrderEntity order;

}
