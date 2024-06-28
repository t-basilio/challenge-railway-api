package com.my_ecommerce.controller;

import com.my_ecommerce.controller.docs.StateDocs;
import com.my_ecommerce.controller.dtos.StateDto;
import com.my_ecommerce.domain.model.State;
import com.my_ecommerce.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController implements StateDocs {

    private final StateService service;

    @Autowired
    public StateController(StateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StateDto>> listing(){
        var statesDto = service.findAll()
                .stream().map(StateDto::new).toList();

        return ResponseEntity.ok(statesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> finding(@PathVariable Long id){
        var state = service.findById(id);
        return ResponseEntity.ok(new StateDto(state));
    }

    @PostMapping
    public ResponseEntity<StateDto> creating(@RequestBody StateDto stateDto){
        var state = service.create(stateDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(state.getId())
                .toUri();
        return ResponseEntity.created(location).body(new StateDto(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDto> updating(@PathVariable Long id, @RequestBody StateDto stateDto){
        var state = service.update(id, stateDto.toModel());
        return ResponseEntity.ok(new StateDto(state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleting(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
