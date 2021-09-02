package com.example.demo.infrastructure.ingredientInfrastructure;

import java.util.*;
import com.example.demo.domain.ingredientDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepositoryImp implements IngredientRepositoryWrite, IngredientRepositoryRead {
    private final IngredientRepositoryJPA ingredientRepositoryJPA;

    @Autowired
    public IngredientRepositoryImp(IngredientRepositoryJPA ingredientRepositoryJPA) {
        this.ingredientRepositoryJPA = ingredientRepositoryJPA;
    }

    @Override
    public void add(Ingredient ingredient) {
        this.ingredientRepositoryJPA.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(UUID id) {
        return this.ingredientRepositoryJPA.findById(id);
    }

    @Override
    public void update(Ingredient ingredient) {
        this.ingredientRepositoryJPA.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        this.ingredientRepositoryJPA.delete(ingredient);
    }

    @Override
    public List<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientRepositoryJPA.findByCriteria(name, PageRequest.of(page, size));
    }

    @Override
    public boolean exists(String name) {
        return this.ingredientRepositoryJPA.exists(name);
    }
}