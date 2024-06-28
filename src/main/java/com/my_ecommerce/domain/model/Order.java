package com.my_ecommerce.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.my_ecommerce.domain.enums.StatusOrder;
import com.my_ecommerce.service.exception.BusinessException;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.CREATED;

    @ManyToOne
    private Client client;

    private BigDecimal totalValue;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ItemOrder> items = new ArrayList<>();

    @Embedded
    private Address orderAddress;

    public void calculateTotalValue(){
        totalValue =  items.stream().map(ItemOrder::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(ItemOrder item) {
        getItems().add(item);
    }

    public void removeItem(ItemOrder item) {
        getItems().remove(item);
    }

    public void finish() {
        setStatus(StatusOrder.FINISHED);
    }

    public void cancel() {
        setStatus(StatusOrder.CANCELLED);
    }

    public void setStatus(StatusOrder newStatus) {
        if(getStatus().notAllowStatusChangeTo(newStatus) && newStatus != StatusOrder.CREATED) {
            throw new BusinessException("Order '%s' cannot change status '%s' to '%s'"
                    .formatted(getId(), getStatus(), newStatus));
        }
        this.status = newStatus;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemOrder> items) {
        this.items = items;
    }

    public Address getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(Address orderAddress) {
        this.orderAddress = orderAddress;
    }

}
