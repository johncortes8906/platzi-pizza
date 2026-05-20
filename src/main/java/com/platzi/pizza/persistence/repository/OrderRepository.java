package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projections.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM orders WHERE customer_id = :customerId", nativeQuery = true)
    List<OrderEntity> findByCustomerId(@Param("customerId") String customerId);

    @Query(value = "SELECT po.order_id AS orderId, cu.name AS customerName, po.date AS orderDate, " +
            "po.total AS orderTotalPrice, GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM orders AS po " +
            "INNER JOIN customers AS cu ON (po.customer_id = cu.customer_id) " +
            "INNER JOIN orders_by_item oi ON po.order_id = oi.order_id " +
            "INNER JOIN pizzas pi ON oi.pizza_id = pi.pizza_id " +
            "WHERE po.order_id = :orderId " +
            "GROUP BY po.order_id, cu.name, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);
}
