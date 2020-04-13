package com.example.formbindig_assignment.data;

import com.example.formbindig_assignment.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoInMemory implements CustomerDao {

    private List<Customer> customerList = new ArrayList();

    @Override
    public Customer save(final Customer customer){
        if(customerList.contains(customer)){
            throw new IllegalArgumentException();
        }
        customerList.add(customer);
        return findById(customer.getCustomerId()).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Optional<Customer> findById(final String customerId){
        return customerList.stream()
                .filter(customer -> customer.getCustomerId().equalsIgnoreCase(customerId))
                .findFirst();
    }

    @Override
    public Optional<Customer> findByEmail(final String email){
        return customerList.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Customer> findAll(){
        return Collections.unmodifiableList(customerList);
    }

    @Override
    public boolean delete(final String customerId){
        Optional<Customer> customer = findById(customerId);
        return customer.map(value -> customerList.remove(value)).orElse(false);
    }


}
