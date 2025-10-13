package com.gyarsilalsolanki011.journeymate.repository;

import com.gyarsilalsolanki011.journeymate.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
