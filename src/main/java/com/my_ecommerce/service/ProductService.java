package com.my_ecommerce.service;

import com.my_ecommerce.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product findById(Long id);

    Product update(Long id, Product product);

    void deleteById(Long id);

    List<Product> findAll();
}
