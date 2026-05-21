package com.platzi.pizza.persistence.entity;

import com.platzi.pizza.persistence.audits.AuditableEntity;
import com.platzi.pizza.persistence.audits.PizzaAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Entity
@Table(name = "pizzas")
@EntityListeners({AuditingEntityListener.class, PizzaAuditListener.class})
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id", nullable = false)
    private Integer pizzaId;
    @Column(nullable = false, length = 30, unique = true)
    private String name;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

}
