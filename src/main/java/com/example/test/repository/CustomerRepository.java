package com.example.test.repository;

import com.example.test.Customer;

import java.util.Optional;

public interface CustomerRepository {
    public CustomerEntity saveOrUpdate(Customer customer);

    public CustomerEntity saveCustomer(CustomerEntity customer );

    public CustomerEntity updateCustomer(int id, CustomerEntity customerEntity);

    public Optional<CustomerEntity> getCustomer(String email);
}
