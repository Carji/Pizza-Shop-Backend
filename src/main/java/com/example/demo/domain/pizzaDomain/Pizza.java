package com.example.demo.domain.pizzaDomain;

import java.math.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.core.EntityBase;
import lombok.*;

@Entity
@Table(name = "pizzas")
public @NoArgsConstructor @Getter @Setter class Pizza extends EntityBase {

    @NotBlank
    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Embedded
    @Column(name = "image")
    private Image image;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, name = "price")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "pizza_ingredients", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal calculatePrice() {
        BigDecimal total = new BigDecimal(0.0);
        BigDecimal addPrice = new BigDecimal(1.2);
        for (Ingredient ingredient : this.ingredients) {
            total = total.add(ingredient.getPrice());
        }
        total = total.multiply(addPrice);
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
}