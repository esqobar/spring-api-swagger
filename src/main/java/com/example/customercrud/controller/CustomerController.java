package com.example.customercrud.controller;


import com.example.customercrud.model.Customer;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    //returns a list of customers
    @GetMapping
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //returns a customer by his/her id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id){

        try {
            Customer customer = customerRepository.findById(id).get();
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // creating or adding a new customer
    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    //update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer){
        customer.setId(id);
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    //delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer id){
        try {
            Customer customer = customerRepository.findById(id).get();
            customerRepository.delete(customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }
    }
}
