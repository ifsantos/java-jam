package com.github.ifsantos.resourceserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.github.ifsantos.resourceserver.model.User;

@Component
public interface IUserRepository extends PagingAndSortingRepository<User,Long>{
    
}
