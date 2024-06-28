package com.my_ecommerce.domain.repository;

import com.my_ecommerce.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
