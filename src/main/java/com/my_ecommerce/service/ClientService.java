package com.my_ecommerce.service;

import com.my_ecommerce.domain.model.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client);

    Client findById(Long id);

    Client update(Long id, Client client);

    void deleteById(Long id);

    List<Client> findAll();
}
