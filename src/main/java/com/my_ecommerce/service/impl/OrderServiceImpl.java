package com.my_ecommerce.service.impl;

import com.my_ecommerce.domain.model.*;
import com.my_ecommerce.domain.repository.CityRepository;
import com.my_ecommerce.domain.repository.ClientRepository;
import com.my_ecommerce.domain.repository.OrderRepository;
import com.my_ecommerce.domain.repository.ProductRepository;
import com.my_ecommerce.service.OrderService;
import com.my_ecommerce.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final CityRepository cityRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository,
                            ProductRepository productRepository,CityRepository cityRepository){
      this.orderRepository = orderRepository;
      this.clientRepository = clientRepository;
      this.productRepository = productRepository;
      this.cityRepository = cityRepository;
    }

    @Override
    public Order create(Order order) {
        validateOrder(order);
        order.calculateTotalValue();
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void addItem(Long orderId, Long productId, Integer quantity) {
        Order order = findById(orderId);
        Product productToAdd = productRepository.findById(productId).orElseThrow(
                () -> new BusinessException("Product id '%d' not founded.".formatted(productId)));

        ItemOrder item = new ItemOrder();
        item.setProduct(productToAdd);
        item.setQuantity(quantity);
        item.setOrder(order);
        order.addItem(item);
        order.calculateTotalValue();
    }

    @Transactional
    public void removeItem(Long orderId, Long itemId) {
        Order order = findById(orderId);

        ItemOrder item = order.getItems().stream()
                .filter(i -> i.getId().equals(itemId)).findFirst().orElseThrow(
                        () -> new BusinessException("Item id '%d' not founded.".formatted(itemId)));
        order.removeItem(item);
        order.calculateTotalValue();
    }

    @Transactional
    public void finish(Long id){
        Order order = findById(id);
        order.finish();
    }

    @Transactional
    public void cancel(Long id){
        Order order = findById(id);
        order.cancel();
    }

    private void validateOrder(Order order) {
        Long clientId = order.getClient().getId();
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new BusinessException("Client id '%d' not founded.".formatted(clientId)));

        Long cityId = order.getOrderAddress().getCity().getId();
        City city = cityRepository.findById(cityId).orElseThrow(
                () -> new BusinessException("City id '%d' not founded.".formatted(cityId)));

        order.setClient(client);
        order.getOrderAddress().setCity(city);

        order.getItems().forEach( item -> {
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow();
            item.setProduct(product);
            item.setOrder(order);
        });
    }
}
