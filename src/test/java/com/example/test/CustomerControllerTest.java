package com.example.test;


import com.example.test.repository.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

//    @Mock
//    private CustomerRepository customerRepository;

//    @InjectMocks
//    private CustomerController controller;

    @Test
    public void givenEmptyFields_whenCreatingCustomer_receiveBadRequest() {
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity("/customer", new Customer(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("not a valid request", responseEntity.getBody());
    }



    @Test
    public void givenNotValidEmailAddress_whenCreatingCustomer_receiveBadRequest() {
        Customer customer = new Customer("testName", "testEmail");
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity("/customer", customer, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("not a valid request", responseEntity.getBody());
    }

    @Test
    public void givenValidFields_whenCreatingCustomer_receiveCustomerInformation() {
        Customer customer = new Customer("testName", "testEmail@email.com");


        ResponseEntity<CustomerEntity> responseEntity =
                restTemplate.postForEntity("/customer", customer, CustomerEntity.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getId());
        assertEquals("testName", responseEntity.getBody().getName());
        assertEquals("testEmail@email.com", responseEntity.getBody().getEmail());

    }

    @Test
    public void givenValidFields_whenUpdatingCustomer_receiveUpdatedCustomerInformation() {
        Customer customer = new Customer("testName", "testEmail@email.com");
        Customer updatedCustomer = new Customer("updatedTestName", "testEmail@email.com");


        ResponseEntity<CustomerEntity> responseEntity =
                restTemplate.postForEntity("/customer", customer, CustomerEntity.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getId());
        assertEquals("testName", responseEntity.getBody().getName());
        assertEquals("testEmail@email.com", responseEntity.getBody().getEmail());

        ResponseEntity<CustomerEntity> updatedResponseEntity =
                restTemplate.postForEntity("/customer", updatedCustomer, CustomerEntity.class);

        assertEquals(HttpStatus.OK, updatedResponseEntity.getStatusCode());
        assertEquals(1, updatedResponseEntity.getBody().getId());
        assertEquals("updatedTestName", updatedResponseEntity.getBody().getName());
        assertEquals("testEmail@email.com", updatedResponseEntity.getBody().getEmail());

    }
}
