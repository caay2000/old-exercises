package com.merkleinc.interviewkata.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.merkleinc.interviewkata.api.CustomerApi;
import com.merkleinc.interviewkata.api.model.Customer;
import com.merkleinc.interviewkata.service.CustomerService;

@RestController
public class CustomerV1Controller {

    private CustomerService customerService;

    @Autowired
    public CustomerV1Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/com/merkleinc/interviewkata/api/internal/customer/{id}")
    public Customer getCustomer(@PathVariable String id) {

        return this.customerService.get(id);
    }
}
