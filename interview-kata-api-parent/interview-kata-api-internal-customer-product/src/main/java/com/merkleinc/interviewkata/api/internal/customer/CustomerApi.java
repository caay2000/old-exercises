package com.merkleinc.interviewkata.api.internal.customer;

import java.util.Optional;
import com.merkleinc.interviewkata.api.internal.customer.model.Customer;

public interface CustomerApi {

    Optional<Customer> getCustomer(String customerId);
}
