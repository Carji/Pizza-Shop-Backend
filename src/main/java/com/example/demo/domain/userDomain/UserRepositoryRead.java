package com.example.demo.domain.userDomain;

import java.util.List;

public interface UserRepositoryRead {
    public List<UserProjection> getAll(String email, int page, int size);
}
