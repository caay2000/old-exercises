package com.merkleinc.interviewkata.controller;

import com.merkleinc.interviewkata.customer.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable String id){

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Merkle");
        customer.setMiddleName("Interview");
        customer.setLastName("Kata");
        return customer;
    }
}
