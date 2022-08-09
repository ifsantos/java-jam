package com.github.ifsantos.resourceserver.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.ifsantos.resourceserver.model.Product;
import com.github.ifsantos.resourceserver.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
    IProductRepository repo;

    public ProductServiceImpl(IProductRepository repo){
        this.repo = repo;
    }

    @Override
    public Optional<Product> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public Product save(Product Product) {
        return repo.save(Product);
    }

    @Override
    public Iterable<Product> list() {
        return repo.findAll();
    }
    
}
