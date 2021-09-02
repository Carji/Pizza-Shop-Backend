package com.example.demo.infrastructure.ingredientInfrastructure;

import java.util.*;
import com.example.demo.domain.ingredientDomain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IngredientRepositoryJPA extends JpaRepository<Ingredient, UUID> {
    Ingredient findByName(@Param("name") String name);
    final String sqlExists = """
                             SELECT case when count(i)> 0 then true else false
                             end from Ingredient i WHERE i.name = :name
                             """;
    final String sqlCriteria = """
                               SELECT i.id as id, i.name as name, i.price as price 
                               FROM Ingredient i WHERE (:name is NULL OR name LIKE %:name%)
                               """;

    @Query(sqlCriteria)
    List<IngredientProjection> findByCriteria(@Param("name") String name, Pageable pageable);

    @Query(sqlExists)
    boolean exists(@Param("name") String name);

}
