package com.my_ecommerce.service.impl;

import com.my_ecommerce.domain.model.City;
import com.my_ecommerce.domain.model.Client;
import com.my_ecommerce.domain.repository.CityRepository;
import com.my_ecommerce.domain.repository.ClientRepository;
import com.my_ecommerce.service.ClientService;
import com.my_ecommerce.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final CityRepository cityRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CityRepository cityRepository){
      this.repository = clientRepository;
      this.cityRepository = cityRepository;
    }

    @Override
    public Client create(Client client) {
        if(repository.existsByPhoneNumber(client.getPhoneNumber())) {
            throw new IllegalArgumentException("This phone number already exists.");
        }
        City city = findOrFailCity(client.getAddress().getCity());

        client.getAddress().setCity(city);
        return repository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Client update(Long id, Client client) {
        if(repository.existsByPhoneNumber(client.getPhoneNumber())) {
            throw new IllegalArgumentException("This phone number already exists.");
        }

        Client clientToUpdate = findById(id);

        changeFields(client, clientToUpdate);
        return repository.save(clientToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Client clientToDelete = findById(id);
        repository.delete(clientToDelete);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    private void changeFields(Client client, Client clientToUpdate) {
        City city = findOrFailCity(client.getAddress().getCity());
        clientToUpdate.getAddress().setCity(city);

        clientToUpdate.setName(client.getName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setPhoneNumber(client.getPhoneNumber());
        clientToUpdate.setAddress(client.getAddress());
    }

    private City findOrFailCity(City city) {
        Long cityId = city.getId();
        return cityRepository.findById(cityId).orElseThrow(
                () -> new BusinessException("City id '%d' not founded".formatted(cityId)));
    }
}
