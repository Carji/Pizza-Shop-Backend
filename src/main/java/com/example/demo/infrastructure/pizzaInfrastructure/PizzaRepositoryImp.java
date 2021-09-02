package com.example.demo.infrastructure.pizzaInfrastructure;

import com.cloudinary.Cloudinary;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.pizzaDomain.*;
import com.example.demo.core.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class PizzaRepositoryImp implements PizzaRepositoryWrite, PizzaRepositoryRead {

    private PizzaRepositoryJPA pizzaRepositoryJPA;
    private final RedisTemplate<String, byte[]> template;

    @Autowired
    public PizzaRepositoryImp(final PizzaRepositoryJPA pizzaRepositoryJPA,
            final RedisTemplate<String, byte[]> template) {
        this.pizzaRepositoryJPA = pizzaRepositoryJPA;
        this.template = template;
    }

    @Override
    public void add(Pizza pizza) {
        Image image = pizza.getImage();
        try {
            byte[] bytes = this.template.opsForValue().get(image.getId().toString());
            // if (bytes==null) {
            // }
            image.setData(bytes);
            Cloudinary cloudinary = new Cloudinary();
            String cloudUrl = cloudinary.url().secure(true).publicId(image.getId()).generate();
            image.setUrl(cloudUrl);
        } catch (Exception e) {
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        } finally {
            if (!this.template.getConnectionFactory().getConnection().isClosed()) {
                this.template.getConnectionFactory().getConnection().close();
            }
        }
        pizza.setImage(image);
        this.pizzaRepositoryJPA.save(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        Image image = pizza.getImage();
        try {
            byte[] bytes = this.template.opsForValue().get(image.getId().toString());
            // if (bytes==null) {
            // }
            image.setData(bytes);
            // Cloudinary cloudinary=new Cloudinary();
            // String cloudUrl=
            // cloudinary.url().secure(true).publicId(image.getId()).generate();
            // image.setUrl(cloudUrl);
        } catch (Exception e) {
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        } finally {
            if (!this.template.getConnectionFactory().getConnection().isClosed()) {
                this.template.getConnectionFactory().getConnection().close();
            }
        }
        pizza.setImage(image);
        this.pizzaRepositoryJPA.save(pizza);
    }

    @Override
    public Optional<Pizza> findById(UUID id) {
        return this.pizzaRepositoryJPA.findById(id);
    }

    @Override
    public List<PizzaProjection> getAll(String name, int page, int size) {
        return this.pizzaRepositoryJPA.findByCriteria(name, PageRequest.of(page, size, Sort.by("name").descending()));
    }

    @Override
    public boolean exists(String name) {
        return this.pizzaRepositoryJPA.exists(name);
    }

    @Override
    public void delete(Pizza pizza) {
        this.pizzaRepositoryJPA.delete(pizza);
    }

}
