package com.my_ecommerce.service.impl;

import com.my_ecommerce.domain.model.State;
import com.my_ecommerce.domain.repository.StateRepository;
import com.my_ecommerce.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository repository;

    @Autowired
    public StateServiceImpl(StateRepository repository){
      this.repository = repository;
    }

    @Override
    public State create(State state) {
        state.setName(state.getName().toUpperCase());
        if(repository.existsByName(state.getName())) {
            throw new IllegalArgumentException("This state name already exists.");
        }
        return repository.save(state);
    }

    @Override
    public State findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public State update(Long id, State state) {
        State stateToUpdate = findById(id);
        stateToUpdate.setName(state.getName());
        return repository.save(stateToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        State stateToDelete = findById(id);
        repository.delete(stateToDelete);
    }

    @Override
    public List<State> findAll() {
        return repository.findAll();
    }
}
