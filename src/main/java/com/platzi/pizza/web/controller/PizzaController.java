package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.service.dtos.UpdatePizzaPriceDto;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements) {

        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{pizzaId}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int pizzaId) {
        return ResponseEntity.ok(this.pizzaService.getById(pizzaId));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizzaEntity) {
        if ((pizzaEntity.getPizzaId() != null) && this.pizzaService.doesPizzaExist(pizzaEntity.getPizzaId())) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        if (!this.pizzaService.doesPizzaExist(pizzaEntity.getPizzaId())) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
    }

    @DeleteMapping("/{pizzaId}")
    public ResponseEntity<Void> deleteByPizzaId(@PathVariable int pizzaId) {
        if (this.pizzaService.doesPizzaExist(pizzaId)) {
            this.pizzaService.deleteById(pizzaId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int elements,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "Asc") String sortDirection) {
        return ResponseEntity.ok(
                this.pizzaService.getAvailablePizzas(page, elements, sortBy, sortDirection)
        );
    }

    @GetMapping("/available/name={name}")
    public ResponseEntity<PizzaEntity> getAvailableByName(@PathVariable String name) {
        return ResponseEntity.ok(
                this.pizzaService.getAvailableByName(name)
        );
    }

    @GetMapping("/available/description={description}")
    public ResponseEntity<List<PizzaEntity>> findAllByDescription(@PathVariable String description) {
        return  ResponseEntity.ok(
                this.pizzaService.findAllByDescription(description)
        );
    }

    @GetMapping("/cheapest")
    public  ResponseEntity<List<PizzaEntity>> findTopCheapestPizzas() {
        return ResponseEntity.ok(
                this.pizzaService.findTopCheapestPizzas()
        );
    }

    @GetMapping ("/available/not/description={description}")
    public ResponseEntity<List<PizzaEntity>> findAllDifferentByDescription(@PathVariable String description) {
        return ResponseEntity.ok(
                this.pizzaService.findAllDifferentOfDescription(description)
        );
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePizzaPrice(@RequestBody UpdatePizzaPriceDto newPizzaPrice) {
        if (!this.pizzaService.doesPizzaExist(newPizzaPrice.getPizzaId())) {
            return ResponseEntity.badRequest().build();
        }

        this.pizzaService.updatePizzaPrice(newPizzaPrice);
        return ResponseEntity.ok().build();
    }
}
