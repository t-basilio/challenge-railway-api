package com.my_ecommerce.service;

import com.my_ecommerce.domain.model.State;
import java.util.List;

public interface StateService {

    State create(State state );

    State findById(Long id);

    State update(Long id, State state);

    void deleteById(Long id);

    List<State> findAll();
}
