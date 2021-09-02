package com.example.demo.application.userApplication;

import java.util.*;

import javax.validation.Valid;

import com.example.demo.domain.userDomain.UserProjection;

public interface UserApplication {
    public UserDTO add(CreateUserDTO dto);

    // public UserDTO addClient(CreateUserDTO dto);

    public UserDTO update(UUID id, UpdateUserDTO dto);

    public UserDTO get(UUID id);

    public void delete(UUID id);

    public List<UserProjection> getAll(String name, int page, int size);

    public UserDTO login(@Valid loginDTO dto);
}