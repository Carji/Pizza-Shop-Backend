package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.example.demo.core.EntityBase;
import lombok.*;

@Entity
public @NoArgsConstructor @Getter @Setter class Ingredient extends EntityBase {
    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false, unique = true) 
    public String name;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    public BigDecimal price;

    @Override
    public String toString() {

        return String.format("Ingredient {id: %s, name: %s, price: %s}", this.getId(), this.getName(), this.getPrice());
    }

}