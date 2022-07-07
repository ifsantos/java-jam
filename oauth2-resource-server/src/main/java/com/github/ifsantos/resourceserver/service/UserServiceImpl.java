package com.github.ifsantos.resourceserver.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.ifsantos.resourceserver.model.User;
import com.github.ifsantos.resourceserver.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
    IUserRepository repo;

    public UserServiceImpl(IUserRepository repo){
        this.repo = repo;
    }

    @Override
    public Optional<User> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public Iterable<User> list() {
        return repo.findAll();
    }
    
}
