package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddressDto(
        @Schema(example = "06090027") String zipcode,
        @Schema(example = "Av. dos Autonomistas") String street,
        @Schema(example = "2001") String number,
        @Schema(example = "Vila Yara") String district,
        CityDto city) {

    public AddressDto(Address model) {
        this(model.getZipcode(), model.getStreet(), model.getNumber(),
                model.getDistrict(), new CityDto(model.getCity()));
    }

    public Address toModel(){
        var address = new Address();
        address.setZipcode(this.zipcode);
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setDistrict(this.district);
        address.setCity(this.city.toModel());
        return address;
    }
}
