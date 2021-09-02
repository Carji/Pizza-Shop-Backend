package com.example.demo.infrastructure.commentInfrastructure;

import java.util.*;
import com.example.demo.domain.commentDomain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface CommentRepositoryJPA extends JpaRepository <Comment, UUID> {
    Comment findByPizza(@Param("name") String name);

    final String sqlCriteria = """
                                SELECT c.id as id, c.text as text, c.rating as rating 
                                FROM Comment c INNER JOIN Pizza p ON c.pizza = p.id
                                WHERE (:name is NULL OR p.name LIKE %:name%)
                             """;

    final String sqlExists = """
                                SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END 
                                FROM Comment c WHERE c.text = :text
                             """;


    @Query(sqlCriteria)
    List<CommentProjection> findByCriteria(@Param("name") String name, Pageable pageable);

    @Query(sqlExists)
    boolean exists(@Param("text") String text);
}