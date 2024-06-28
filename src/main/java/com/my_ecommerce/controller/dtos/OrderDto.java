package com.my_ecommerce.controller.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.my_ecommerce.domain.enums.StatusOrder;
import com.my_ecommerce.domain.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        @Schema(example = "1") Long id,
        LocalDateTime creationDate, ClientDto client,
        @Schema(example = "95000") BigDecimal totalValue,
        @Schema(example = "CREATED") StatusOrder status,
        List<ItemOrderDto> items,
        AddressDto orderAddress) {

    public OrderDto(Order model) {
        this(model.getId(), model.getCreationDate(), new ClientDto(model.getClient()),
                model.getTotalValue(), model.getStatus(),
                model.getItems().stream().map(ItemOrderDto::new).toList(),
                new AddressDto(model.getOrderAddress()));
    }

    public Order toModel() {
        var order = new Order();
        order.setId(this.id);
        order.setCreationDate(this.creationDate);
        order.setClient(this.client.toModel());
        order.setStatus(this.status);
        order.calculateTotalValue();
        order.setItems(this.items.stream().map(ItemOrderDto::toModel).toList());
        order.setOrderAddress(this.orderAddress.toModel());
        return order;
    }
}
