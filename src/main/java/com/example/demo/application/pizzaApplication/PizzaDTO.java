package com.example.demo.application.pizzaApplication;

import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.ingredientDomain.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public @NoArgsConstructor @Getter @Setter
class PizzaDTO {

    private UUID id;

    private String name;

    private Image image;

    private BigDecimal price;

    private Set<Ingredient> ingredients = new HashSet<Ingredient>();
}
