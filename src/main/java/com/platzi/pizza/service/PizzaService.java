package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity getById(int pizzaId) {
        return this.pizzaRepository.findById(pizzaId).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public boolean doesPizzaExist(int pizzaId) {
        return this.pizzaRepository.existsById(pizzaId);
    }

    public void deleteById(int pizzaId) {
        this.pizzaRepository.deleteById(pizzaId);
    }

    public List<PizzaEntity> getAvailablePizzas() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getAvailableByName(String name) {
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> findAllByDescription(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> findAllDifferentOfDescription(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }
}
