package com.example.demo.infrastructure.pizzaInfrastructure;

import java.util.*;
import com.example.demo.domain.pizzaDomain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface PizzaRepositoryJPA extends JpaRepository<Pizza, UUID> {
    @Query("""
              SELECT p.id as id, p.name as name, p.price as price 
              FROM Pizza p 
              WHERE (:name is NULL OR name LIKE %:name%)
           """)
    List<PizzaProjection>  findByCriteria(@Param("name") String name, Pageable pageable);

    @Query("""
              SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END 
              FROM Pizza p WHERE p.name = :name
           """)
    boolean exists(@Param("name") String name);

    @Query("SELECT p FROM Pizza p WHERE id = :id")
    PizzaIngredientProjection findByPizzaId(
            @Param("id") UUID id
    );
}
