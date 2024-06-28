package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.ItemOrder;
import io.swagger.v3.oas.annotations.media.Schema;

public record ItemOrderDto(
        @Schema(example = "1") Long id,
        @Schema(example = "10") Integer quantity,
        ProductDto product) {

    public ItemOrderDto(ItemOrder model) {
        this(model.getId(), model.getQuantity(), new ProductDto(model.getProduct()));
    }

    public ItemOrder toModel() {
        var item = new ItemOrder();
        item.setId(this.id);
        item.setQuantity(this.quantity);
        item.setProduct(this.product.toModel());
        return item;
    }
}
