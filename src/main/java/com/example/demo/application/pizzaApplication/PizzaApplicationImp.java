package com.example.demo.application.pizzaApplication;

import java.util.*;
import com.example.demo.application.imageApplication.ImageDTO;
import com.example.demo.application.ingredientApplication.IngredientApplicationImp;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.ingredientDomain.*;
import com.example.demo.domain.pizzaDomain.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication {


    private final PizzaRepositoryWrite pizzaRepositoryWrite;
    private final PizzaRepositoryRead pizzaRepositoryRead;
    private final IngredientRepositoryWrite ingredientRepositoryWrite;
    private final IngredientRepositoryRead ingredientRepositoryRead;
    private final IngredientApplicationImp ingredientApplicationImp;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaRepositoryWrite pizzaRepositoryWrite, 
        final PizzaRepositoryRead pizzaRepositoryRead, final IngredientRepositoryWrite ingredientRepositoryWrite, 
        final IngredientRepositoryRead ingredientRepositoryRead, final ModelMapper modelMapper, 
        final Logger logger, final IngredientApplicationImp ingredientApplicationImp) {
        super((id) -> pizzaRepositoryWrite.findById(id));
        this.pizzaRepositoryWrite = pizzaRepositoryWrite;
        this.pizzaRepositoryRead = pizzaRepositoryRead;
        this.ingredientRepositoryWrite = ingredientRepositoryWrite;
        this.ingredientRepositoryRead = ingredientRepositoryRead;
        this.modelMapper = modelMapper;
        this.logger = logger;
        this.ingredientApplicationImp = ingredientApplicationImp;
    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {
        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        for (UUID ingredientId : dto.getIngredients()) {
            Ingredient ingredient = this.modelMapper.map(ingredientApplicationImp.get(ingredientId), Ingredient.class);
            pizza.addIngredient(ingredient);
        }
        pizza.setPrice(pizza.calculatePrice());
        Image image= new Image();
        image.setId(dto.getImageId());
        pizza.validate("name", pizza.getName(), (name) -> this.pizzaRepositoryWrite.exists(name));
        this.pizzaRepositoryWrite.add(pizza);
        logger.info(this.serializeObject(pizza, " added."));
        ImageDTO imageDTO=this.modelMapper.map(image, ImageDTO.class);
        PizzaDTO pizzaDTO= this.modelMapper.map(pizza, PizzaDTO.class);
        pizzaDTO.setImageDTO(imageDTO);
        return pizzaDTO;
    }

    @Override
    public PizzaDTO get(UUID id) {
        Pizza pizza = this.findById(id);
        return this.modelMapper.map(pizza, PizzaDTO.class);
    }

    @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaRepositoryRead.getAll(name, page, size);
    }

    @Override
    public PizzaDTO update(UUID id, CreateOrUpdatePizzaDTO dto) {
        Pizza pizza = this.findById(id);
        Image image=pizza.getImage();
        if(this.pizzaRepositoryWrite.exists(pizza.getName())) {
            pizza.validate();
        } else {
            pizza.validate("name", pizza.getName(), (name)-> this.pizzaRepositoryWrite.exists(name));
        }
        if (dto.imageId!=null){
            image.setId(dto.getImageId());
        }
        pizza.setName(dto.getName());
        this.pizzaRepositoryWrite.update(pizza);
        logger.info(this.serializeObject(pizza, " updated"));
        PizzaDTO pizzaDTO= this.modelMapper.map(pizza, PizzaDTO.class);
        ImageDTO imageDTO=this.modelMapper.map(image, ImageDTO.class);
        pizzaDTO.setImageDTO(imageDTO);
        return pizzaDTO;
    }

    @Override
    public void delete(UUID id) {
        Pizza pizza = this.findById(id);
        this.pizzaRepositoryWrite.delete(pizza);
        logger.info(this.serializeObject(pizza, " deleted"));
    }

    public String serializeObject(Pizza pizza, String message){
        return String.format("Pizza {id: %s, name: %s, price: %s} %s succesfully.",
                pizza.getId(), pizza.getName(),
                pizza.getPrice().toString(),
                message);
    }
}
