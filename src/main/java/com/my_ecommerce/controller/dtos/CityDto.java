package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.City;
import io.swagger.v3.oas.annotations.media.Schema;

public record CityDto(
        @Schema(example = "1") Long id,
        @Schema(example = "Osasco") String name,
        StateDto state) {

    public CityDto(City model) {
        this(model.getId(), model.getName(), new StateDto(model.getState()));
    }

    public City toModel() {
        var city = new City();
        city.setId(this.id);
        city.setName(this.name);
        city.setState(this.state.toModel());
        return city;
    }
}
