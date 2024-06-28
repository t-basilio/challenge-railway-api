package com.my_ecommerce.service;

import com.my_ecommerce.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    Order findById(Long id);

    List<Order> findAll();

    void addItem(Long orderId, Long productId, Integer quantity);

    void removeItem(Long orderId, Long itemId);

    void finish(Long id);

    void cancel(Long id);

}
