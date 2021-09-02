package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import lombok.*;

@Validated
public @NoArgsConstructor @Getter @Setter class CreateOrUpdateIngredientDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    public String name;

    @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal price;
}
