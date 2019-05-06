package com.merkleinc.interviewkata.customer;

import java.util.Optional;
import com.merkleinc.interviewkata.customer.model.Customer;

public interface CustomerApi {

    Optional<Customer> getCustomer(String customerId);
}
