package com.example.demo.domain.ingredientDomain;

import java.util.List;

public interface IngredientRepositoryRead {
    public List<IngredientProjection> getAll(String name, int page, int size);
}
