package com.merkleinc.interviewkata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merkleinc.interviewkata.api.CustomerApi;
import com.merkleinc.interviewkata.api.model.Customer;

@Service("customerService")
public class CustomerService implements CustomerApi {

    @Autowired
    private CustomerApi customerApplication;

    @Override
    public Customer get(String id) {

        return customerApplication.get(id);
    }
}
