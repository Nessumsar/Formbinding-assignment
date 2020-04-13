package com.example.formbindig_assignment.data;

import com.example.formbindig_assignment.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    Customer save (Customer customer);
    Optional<Customer> findById(String customerId);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    boolean delete(String customerId);
}
