package com.my_ecommerce.controller;

import com.my_ecommerce.controller.docs.OrderDocs;
import com.my_ecommerce.controller.dtos.OrderDto;
import com.my_ecommerce.domain.model.Order;
import com.my_ecommerce.controller.dtos.InputItem;
import com.my_ecommerce.controller.dtos.ItemOrderId;
import com.my_ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderDocs {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> listing(){
        var ordersDto = service.findAll().stream().map(OrderDto::new).toList();
        return ResponseEntity.ok(ordersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> finding(@PathVariable Long id){
        var order = service.findById(id);
        return ResponseEntity.ok(new OrderDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderDto> creating(@RequestBody OrderDto orderDto){
        var order = service.create(orderDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).body(new OrderDto(order));
    }

    @PutMapping("/{id}/add-item")
    public ResponseEntity<Void> addingItem(@PathVariable Long id, @RequestBody InputItem item){
        service.addItem(id, item.productId(), item.quantity());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/remove-item")
    public ResponseEntity<Void> removingItem(@PathVariable Long id, @RequestBody ItemOrderId item){
        service.removeItem(id, item.itemId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<Void> finishing(@PathVariable Long id){
        service.finish(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelling(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
