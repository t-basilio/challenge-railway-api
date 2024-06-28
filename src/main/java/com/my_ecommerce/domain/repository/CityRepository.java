package com.my_ecommerce.domain.repository;

import com.my_ecommerce.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    boolean existsByName(String name);
}
