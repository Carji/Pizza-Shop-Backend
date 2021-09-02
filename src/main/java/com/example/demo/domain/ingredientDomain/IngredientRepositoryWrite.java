package com.example.demo.domain.ingredientDomain;

import java.util.UUID;
import com.example.demo.core.*;

public interface IngredientRepositoryWrite extends FindById<Ingredient, UUID>, ExistsByField {
    public void add(Ingredient ingredient);
    public void update(Ingredient ingredient);
    public void delete(Ingredient ingredient);
}
