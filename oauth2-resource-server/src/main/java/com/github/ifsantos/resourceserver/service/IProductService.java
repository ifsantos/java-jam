package com.github.ifsantos.resourceserver.service;

import java.util.Optional;

import com.github.ifsantos.resourceserver.model.Product;

public interface IProductService {
    Optional<Product> findById(long id);

    Product save(Product Product);

    Iterable<Product> list();
}
