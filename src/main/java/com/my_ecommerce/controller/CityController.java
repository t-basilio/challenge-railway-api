package com.my_ecommerce.controller;

import com.my_ecommerce.controller.docs.CityDocs;
import com.my_ecommerce.controller.dtos.CityDto;
import com.my_ecommerce.domain.model.City;
import com.my_ecommerce.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController implements CityDocs {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> listing(){
        var citiesDto = service.findAll().stream().map(CityDto::new).toList();
        return ResponseEntity.ok(citiesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> finding(@PathVariable Long id){
        var city = service.findById(id);
        return ResponseEntity.ok(new CityDto(city));
    }

    @PostMapping
    public ResponseEntity<CityDto> creating(@RequestBody CityDto cityDto){
        var city = service.create(cityDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();
        return ResponseEntity.created(location).body(new CityDto(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updating(@PathVariable Long id, @RequestBody CityDto cityDto){
        var city = service.update(id, cityDto.toModel());
        return ResponseEntity.ok(new CityDto(city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleting(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
