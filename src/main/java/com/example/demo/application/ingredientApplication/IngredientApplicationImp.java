package com.example.demo.application.ingredientApplication;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import java.util.*;

@Service
public class IngredientApplicationImp extends ApplicationBase<Ingredient, UUID> implements IngredientApplication {

    private final IngredientRepositoryWrite ingredientRepositoryWrite;
    private final IngredientRepositoryRead ingredientRepositoryRead;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientRepositoryWrite ingredientRepositoryWrite,
            final IngredientRepositoryRead ingredientRepositoryRead, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> ingredientRepositoryWrite.findById(id));

        this.ingredientRepositoryWrite = ingredientRepositoryWrite;
        this.ingredientRepositoryRead = ingredientRepositoryRead;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        ingredient.validate("name", ingredient.getName(), (name) -> this.ingredientRepositoryWrite.exists(name));

        this.ingredientRepositoryWrite.add(ingredient);
        logger.info(this.serializeObject(ingredient, "inserted"));

        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public IngredientDTO get(UUID id) {

        Ingredient ingredient = this.findById(id);
        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public IngredientDTO update(UUID id, CreateOrUpdateIngredientDTO dto) {

        Ingredient oldIng = this.findById(id);
        Ingredient newIng = modelMapper.map(dto, Ingredient.class);
        newIng.setId(id);

        if (oldIng.getName().equals(dto.getName())) {
            newIng.validate();
        } else {
            newIng.validate("name", newIng.getName(), (name) -> this.ingredientRepositoryWrite.exists(name));
        }
        this.ingredientRepositoryWrite.update(newIng);
        logger.info(this.serializeObject(newIng, "updated"));

        return this.modelMapper.map(newIng, IngredientDTO.class);
    }

    @Override
    public List<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientRepositoryRead.getAll(name, page, size);
    }

    @Override
    public void delete(UUID id) {

        Ingredient ingredient = this.findById(id);
        this.ingredientRepositoryWrite.delete(ingredient);
        logger.info(this.serializeObject(ingredient, "deleted"));
    }
}
