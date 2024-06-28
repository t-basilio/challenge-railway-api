package com.my_ecommerce.controller.dtos;

import com.my_ecommerce.domain.model.Address;
import com.my_ecommerce.domain.model.Client;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClientDto(
        @Schema(example = "1") Long id,
        @Schema(example = "James Gosling") String name,
        @Schema(example = "james@java.com") String email,
        @Schema(example = "11912349876") String phoneNumber,
        AddressDto address) {

    public ClientDto(Client model) {
        this(model.getId(), model.getName(), model.getEmail(),
                model.getPhoneNumber(), new AddressDto(model.getAddress()));
    }

    public Client toModel() {
        var client = new Client();
        client.setId(this.id);
        client.setName(this.name);
        client.setEmail(this.email);
        client.setPhoneNumber(this.phoneNumber);
        client.setAddress(this.address.toModel());
        return client;
    }
}
