package com.my_ecommerce.service.impl;

import com.my_ecommerce.domain.model.City;
import com.my_ecommerce.domain.model.State;
import com.my_ecommerce.domain.repository.CityRepository;
import com.my_ecommerce.domain.repository.StateRepository;
import com.my_ecommerce.service.CityService;
import com.my_ecommerce.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, StateRepository stateRepository){
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public City create(City city) {
        State state = findOrFailState(city.getState());

        city.setState(state);
        city.setName(toCamelCase(city.getName()));

        if(cityRepository.existsByName(city.getName())) {
            throw new IllegalArgumentException("This city name already exists.");
        }

        return cityRepository.save(city);
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public City update(Long id, City city) {
        City cityToUpdate = findById(id);

        State state = findOrFailState(city.getState());

        cityToUpdate.setState(state);
        cityToUpdate.setName(toCamelCase(city.getName()));
        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        City cityToDelete = findById(id);
        cityRepository.delete(cityToDelete);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    private String toCamelCase(String s) {
        return Arrays.stream(s.split("\\s+"))
                .map(p -> p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    private State findOrFailState(State state) {
        Long stateId = state.getId();
        return stateRepository.findById(stateId).orElseThrow(
                () -> new BusinessException("State id '%d' not found".formatted(stateId)));
    }
}
