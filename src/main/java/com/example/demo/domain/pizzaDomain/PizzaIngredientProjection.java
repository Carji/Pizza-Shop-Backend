package com.example.demo.domain.pizzaDomain;

import java.math.BigDecimal;
import java.util.*;

public interface PizzaIngredientProjection {
    public UUID getId();

    public String getName();

    public BigDecimal getPrice();

    public List<Ingredient> getIngredients();

    public Image getImage();

    public interface Image {
        public String getPublic_id();
    };

    public interface Ingredient {

        public UUID getId();

        public String getName();
    }

}
