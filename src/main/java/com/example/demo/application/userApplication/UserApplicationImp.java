package com.example.demo.application.userApplication;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.userDomain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import java.util.*;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {
    private final UserRepositoryRead userRepositoryRead;
    private final UserRepositoryWrite userRepositoryWrite;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp(final UserRepositoryRead userRepositoryRead,
            final UserRepositoryWrite userRepositoryWrite, final ModelMapper modelMapper, final Logger logger) {

        super((id) -> userRepositoryWrite.findById(id));

        this.userRepositoryRead = userRepositoryRead;
        this.userRepositoryWrite = userRepositoryWrite;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public UserDTO add(CreateUserDTO dto) {

        User user = modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        user.validate("email", user.getEmail(), (email) -> this.userRepositoryWrite.exists(email));
        //user.setRole(Role.USER);
        this.userRepositoryWrite.add(user);
        logger.info(this.serializeObject(user, "added"));

        return modelMapper.map(user, UserDTO.class);
    }


//     @Override
//     public UserDTO addClient(CreateUserDTO dto) {

//         User user = modelMapper.map(dto, User.class);
//         user.setId(UUID.randomUUID());
//         user.setRole(Roles.USER);
//         user.validate("email", user.getEmail(), (email) -> this.userRepositoryWrite.exists(email));
//         user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
// //        user.setRole(Roles.USER);

//         this.userRepositoryWrite.add(user);
//         logger.info(this.serializeObject(user, "has been added"));

//         return modelMapper.map(user, UserDTO.class);
//     }

    @Override
    public UserDTO get(UUID id) {
        User user = this.findById(id);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO update(UUID id, UpdateUserDTO dto) {

        User user = this.findById(id);
        User newUser = this.modelMapper.map(dto, User.class);
        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());

        if (BCrypt.checkpw(newUser.getPassword(), user.getPassword())) {
            newUser.setPassword(user.getPassword());
        } else {
            newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
        }
        newUser.validate();
        this.userRepositoryWrite.update(newUser);
        logger.info(this.serializeObject(newUser, "updated"));

        return modelMapper.map(newUser, UserDTO.class);
    }

    @Override
    public void delete(UUID id) {

        User user = this.findById(id);
        this.userRepositoryWrite.delete(user);
        logger.info(this.serializeObject(user, "deleted"));
    }

    @Override
    public List<UserProjection> getAll(String email, int page, int size) {
        return this.userRepositoryRead.getAll(email, page, size);
    }

    @Override
    public UserDTO login(loginDTO dto) {
        // TODO: complete login method
        return null;
    }
}