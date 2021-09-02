package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface IngredientProjection {
    UUID getId();
    String getName();
    public BigDecimal getPrice();
}

