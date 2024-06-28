package com.my_ecommerce.domain.repository;

import com.my_ecommerce.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {

    boolean existsByName(String name);
}
