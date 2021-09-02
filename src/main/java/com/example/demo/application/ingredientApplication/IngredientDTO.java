package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

public @NoArgsConstructor @Getter @Setter class IngredientDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
}