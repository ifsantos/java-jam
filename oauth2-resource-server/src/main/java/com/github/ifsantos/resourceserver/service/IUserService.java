package com.github.ifsantos.resourceserver.service;

import java.util.Optional;

import com.github.ifsantos.resourceserver.model.User;

public interface IUserService {
    Optional<User> findById(long id);

    User save(User user);

    Iterable<User> list();
}
