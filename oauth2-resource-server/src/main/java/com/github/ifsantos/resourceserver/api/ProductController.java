package com.github.ifsantos.resourceserver.api;

import static java.util.stream.Collectors.toList;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.ifsantos.resourceserver.dto.ProductDTO;
import com.github.ifsantos.resourceserver.model.Product;
import com.github.ifsantos.resourceserver.service.IProductService;



@RestController
@RequestMapping("api/product")
public class ProductController {
    
    private IProductService svc;

    public ProductController(IProductService svc) {
        this.svc = svc;
    }

    @GetMapping(value="/{id}")
    public ProductDTO getProduct(@PathVariable("{id}") Long id) {
        Product product = svc.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toDTO(product);
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO Product) {
        Product entity = svc.save(toEntity(Product));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(entity));
    }
    
  
    @GetMapping
    public Iterable<ProductDTO> listProduct() {
        return StreamSupport
            .stream(svc.list().spliterator(),false)
            .map(this::toDTO)
            .collect(toList());
    }
    
    public ProductDTO toDTO(Product u){
        return new ProductDTO(u.getID(), u.getName(), u.getPrice());
    }
    public Product toEntity(ProductDTO u){
        return new Product(u.getId(), u.getName(), u.getPrice());
    }

}
