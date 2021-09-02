package com.example.demo.domain.userDomain;

import java.util.UUID;
import com.example.demo.core.*;

public interface UserRepositoryWrite extends FindById<User, UUID>, ExistsByField {
    public void add(User user);

    public void update(User user);

    public void delete(User user);
}
