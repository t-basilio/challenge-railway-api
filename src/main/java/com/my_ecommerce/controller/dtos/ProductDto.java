package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductDto(
        @Schema(example = "1") Long id,
        @Schema(example = "Iphone 15") String name,
        @Schema(example = "This is your new iPhone") String description,
        @Schema(example = "9500.00") BigDecimal price) {

    public ProductDto(Product model) {
        this(model.getId(), model.getName(), model.getDescription(), model.getPrice());
    }

    public Product toModel() {
        var model = new Product();
        model.setId(this.id);
        model.setName(this.name);
        model.setDescription(this.description);
        model.setPrice(this.price);
        return model;
    }


}
