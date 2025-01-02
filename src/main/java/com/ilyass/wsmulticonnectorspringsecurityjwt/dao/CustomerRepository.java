package com.ilyass.wsmulticonnectorspringsecurityjwt.dao;

import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentityRef(String identityRef);

    Optional<Customer> findByUsername(String username);
}
