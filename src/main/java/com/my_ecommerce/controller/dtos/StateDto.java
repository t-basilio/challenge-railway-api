package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.State;
import io.swagger.v3.oas.annotations.media.Schema;

public record StateDto(
        @Schema(example = "1") Long id,
        @Schema(example = "São Paulo") String name) {

    public StateDto(State model) {
        this(model.getId(), model.getName());
    }

    public State toModel() {
        var state = new State();
        state.setId(this.id);
        state.setName(this.name);
        return state;
    }
}
