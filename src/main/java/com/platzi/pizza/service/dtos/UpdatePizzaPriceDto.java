package com.platzi.pizza.service.dtos;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double price;
}
