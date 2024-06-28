package com.my_ecommerce.controller;

import com.my_ecommerce.controller.docs.ClientDocs;
import com.my_ecommerce.controller.dtos.ClientDto;
import com.my_ecommerce.domain.model.Client;
import com.my_ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController implements ClientDocs {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> listing(){
        var clientsDto = service.findAll().stream().map(ClientDto::new).toList();
        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> finding(@PathVariable Long id){
        var client = service.findById(id);
        return ResponseEntity.ok(new ClientDto(client));
    }

    @PostMapping
    public ResponseEntity<ClientDto> creating(@RequestBody ClientDto clientDto){
        var client = service.create(clientDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ClientDto(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updating(@PathVariable Long id, @RequestBody ClientDto clientDto){
        var client = service.update(id, clientDto.toModel());
        return ResponseEntity.ok(new ClientDto(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleting(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
