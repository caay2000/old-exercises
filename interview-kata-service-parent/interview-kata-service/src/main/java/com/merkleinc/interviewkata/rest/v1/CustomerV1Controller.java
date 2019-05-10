package com.merkleinc.interviewkata.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.merkleinc.interviewkata.api.model.Customer;
import com.merkleinc.interviewkata.application.exception.NotFoundException;
import com.merkleinc.interviewkata.rest.model.ErrorResponse;
import com.merkleinc.interviewkata.service.CustomerService;

@RestController
public class CustomerV1Controller {

    private CustomerService customerService;

    @Autowired
    public CustomerV1Controller(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundException(NotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable String id) {

        return this.customerService.get(id);
    }
}
