package com.example.demo.infrastructure.userInfrastructure;

import java.util.*;
import com.example.demo.domain.userDomain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

public interface UserRepositoryJPA extends JpaRepository<User, UUID> {
    final String sqlExists = """
               SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END
               FROM User u WHERE u.email = :email
            """;
    @Query(sqlExists)
    boolean exists(@Param("email") String email);

    final String sqlByMail = """
                                SELECT u.id as id, u.name as name, u.surname as surname, u.email as email 
                                FROM User u WHERE (:email is NULL OR u.email LIKE %:email%)
                             """;
    @Query(sqlByMail)
    List<UserProjection> findByCriteria(@Param("email") String email, Pageable pageable);
}
