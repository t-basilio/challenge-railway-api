package com.my_ecommerce.controller;

import com.my_ecommerce.controller.docs.ProductDocs;
import com.my_ecommerce.controller.dtos.ProductDto;
import com.my_ecommerce.domain.model.Product;
import com.my_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductDocs {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listing(){
        var products = service.findAll();
        var productsDto = products.stream().map(ProductDto::new).toList();
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> finding(@PathVariable Long id){
        var product = service.findById(id);
        return ResponseEntity.ok(new ProductDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> creating(@RequestBody ProductDto productDto){
        var product = service.create(productDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ProductDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updating(@PathVariable Long id, @RequestBody ProductDto productDto){
        var product = service.update(id, productDto.toModel());
        return ResponseEntity.ok(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleting(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
