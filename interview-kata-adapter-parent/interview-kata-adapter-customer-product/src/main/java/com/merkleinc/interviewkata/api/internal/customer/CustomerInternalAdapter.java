package com.merkleinc.interviewkata.api.internal.customer;

import java.util.Optional;
import javax.inject.Named;
import com.merkleinc.interviewkata.api.internal.customer.model.Customer;
import com.merkleinc.interviewkata.api.internal.customer.translator.CustomerTranslator;
import com.merkleinc.interviewkata.repository.CustomerApi;
import com.merkleinc.interviewkata.repository.exception.RepositoryException;

@Named("customerAdapter")
public class CustomerInternalAdapter implements CustomerInternalApi {

    private final CustomerApi customerRepository;
    private final CustomerTranslator customerTranslator;

    public CustomerInternalAdapter(CustomerApi customerRepository) {
        this.customerRepository = customerRepository;
        this.customerTranslator = new CustomerTranslator();
    }

    @Override
    public Optional<Customer> getCustomer(String customerId) {

        if (customerId == null) {
            throw new IllegalArgumentException("customerId should not be null");
        }

        try {
            return Optional.of(customerTranslator.translate(this.customerRepository.getCustomer(customerId)));
        } catch (RepositoryException e) {
            return Optional.empty();
        }
    }
}
