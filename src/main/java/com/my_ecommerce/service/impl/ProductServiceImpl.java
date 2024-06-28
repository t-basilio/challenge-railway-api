package com.my_ecommerce.service.impl;

import com.my_ecommerce.domain.model.Product;
import com.my_ecommerce.domain.repository.ProductRepository;
import com.my_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository){
      this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productToUpdate = findById(id);
        changeFields(product, productToUpdate);

        return repository.save(productToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Product productToDelete = findById(id);
        repository.delete(productToDelete);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    private void changeFields(Product product, Product productToUpdate) {
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
    }
}
