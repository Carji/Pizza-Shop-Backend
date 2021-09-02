package com.example.demo.application.pizzaApplication;

import java.util.*;

import com.example.demo.domain.pizzaDomain.PizzaProjection;

public interface PizzaApplication {

    public PizzaDTO add(CreateOrUpdatePizzaDTO dto);
    public PizzaDTO get(UUID id);
    public PizzaDTO update (UUID id, CreateOrUpdatePizzaDTO dto);
    public void delete (UUID id);
    public List<PizzaProjection> getAll(String name, int page, int size);
}
