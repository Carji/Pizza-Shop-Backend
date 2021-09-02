package com.example.demo.application.pizzaApplication;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public @NoArgsConstructor
@Getter
@Setter
class CreateOrUpdatePizzaDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    @Column(nullable = false, unique = true)
    public String name;
    @Column(nullable = false)
    public Set<UUID> ingredients;

    @Column(nullable = false)
    public UUID imageId;
}