package com.my_ecommerce.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record InputItem (
        @Schema(example = "10") Integer quantity,
        @Schema(example = "1") Long productId) {
}
