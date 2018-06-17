package com.example.test;

import com.example.test.repository.CustomerEntity;
import com.example.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody @Valid final Customer customer, final BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("not a valid request");
        }
        CustomerEntity entity = customerRepository.saveOrUpdate(customer);
        return ResponseEntity.ok(entity);
    }


    @GetMapping("/{email}")
    public ResponseEntity getCustomer(@PathVariable("email") String email){

        return ResponseEntity.ok(customerRepository.getCustomer(email).get());
    }

}
