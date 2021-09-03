package com.example.demo.infrastructure.pizzaInfrastructure;

import java.util.*;
import com.example.demo.domain.pizzaDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepositoryImp implements PizzaRepositoryWrite, PizzaRepositoryRead {

    private PizzaRepositoryJPA pizzaRepositoryJPA;

    @Autowired
    public PizzaRepositoryImp(final PizzaRepositoryJPA pizzaRepositoryJPA) {
        this.pizzaRepositoryJPA = pizzaRepositoryJPA;
    }

    @Override
    public void add(Pizza pizza) {
        this.pizzaRepositoryJPA.save(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        this.pizzaRepositoryJPA.save(pizza);
    }

    @Override
    public Optional<Pizza> findById(UUID id) {
        return this.pizzaRepositoryJPA.findById(id);
    }

    @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaRepositoryJPA.findByCriteria(name, PageRequest.of(page, size, Sort.by("name").descending()));
    }

    @Override
    public boolean exists(String name) {
        return this.pizzaRepositoryJPA.exists(name);
    }

    @Override
    public void delete(Pizza pizza) {
        this.pizzaRepositoryJPA.delete(pizza);
    }

}
