package com.platzi.pizza.persistence.projections;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getOrderId();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotalPrice();
    String getPizzaNames();
}
