package com.example.demo.domain.pizzaDomain;

import com.example.demo.core.*;
import java.util.*;

public interface PizzaRepositoryWrite extends FindById<Pizza, UUID>, ExistsByField {
    public void add(Pizza pizza);

    public void update(Pizza pizza);

    public void delete(Pizza pizza);

    public Optional<Pizza> findById(UUID id);
}
