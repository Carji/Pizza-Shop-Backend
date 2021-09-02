package com.example.demo.infrastructure.userInfrastructure;

import java.util.*;
import com.example.demo.domain.userDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserRepositoryWrite, UserRepositoryRead {
    private final UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UserRepositoryImp(final UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public void add(User user) {
        this.userRepositoryJPA.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.userRepositoryJPA.findById(id);
    }

    @Override
    public void update(User user) {
        this.userRepositoryJPA.save(user);
    }

    @Override
    public boolean exists(String email) {
        return this.userRepositoryJPA.exists(email);
    }

    @Override
    public void delete(User user) {
        this.userRepositoryJPA.delete(user);
    }

    @Override
    public List<UserProjection> getAll(String email, int page, int size) {
        return this.userRepositoryJPA.findByCriteria(email, PageRequest.of(page, size));
    }
}
