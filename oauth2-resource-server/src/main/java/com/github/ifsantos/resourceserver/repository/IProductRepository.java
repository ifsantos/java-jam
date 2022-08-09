package com.github.ifsantos.resourceserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.github.ifsantos.resourceserver.model.Product;

@Component
public interface IProductRepository extends PagingAndSortingRepository<Product, Long>{ }
