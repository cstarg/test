package com.example.test.repository;


import com.example.test.Customer;
import com.example.test.CustomerMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository{
    @PersistenceContext
    EntityManager entityManager;

    public CustomerEntity saveOrUpdate(Customer customer){
        CustomerEntity entity = CustomerMapper.toCustomerEntity(customer);
        Optional<CustomerEntity> customerFromDb = getCustomer((entity.getEmail()));
        if(customerFromDb.isPresent()){
            //update
            return updateCustomer(customerFromDb.get().getId(), entity);
        }
        else {
            //save
            return saveCustomer(entity);
        }
    }

    public Optional<CustomerEntity> getCustomer(String email){
        List<CustomerEntity> customers = entityManager.createQuery(
                "SELECT c FROM CustomerEntity c WHERE c.email = :email")
                .setParameter("email", email)
                .getResultList();
        return customers.isEmpty()? Optional.empty():Optional.of(customers.get(0));
    }

    public CustomerEntity saveCustomer(CustomerEntity customer ){
        entityManager.persist(customer);
        return customer;
    }

    public CustomerEntity updateCustomer(int id, CustomerEntity customerEntity){
        customerEntity.setId(id);
        entityManager.merge(customerEntity);
        return customerEntity;
    }
}
