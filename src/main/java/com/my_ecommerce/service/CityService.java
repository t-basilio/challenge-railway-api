package com.my_ecommerce.service;

import com.my_ecommerce.domain.model.City;

import java.util.List;

public interface CityService {

    City create(City state );

    City findById(Long id);

    City update(Long id, City city);

    void deleteById(Long id);

    List<City> findAll();
}
