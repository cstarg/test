package com.example.test;

import com.example.test.repository.CustomerEntity;

public class CustomerMapper {
    public static CustomerEntity toCustomerEntity(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }
}
