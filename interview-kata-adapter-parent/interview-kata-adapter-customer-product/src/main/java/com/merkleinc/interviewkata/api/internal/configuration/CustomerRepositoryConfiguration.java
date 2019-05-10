package com.merkleinc.interviewkata.api.internal.configuration;

import java.io.IOException;
import java.util.List;
import javax.inject.Named;
import com.merkleinc.interviewkata.repository.CustomerApi;
import com.merkleinc.interviewkata.repository.exception.RepositoryException;
import com.merkleinc.interviewkata.repository.model.Customer;
import com.merkleinc.interviewkata.repository.model.CustomerProduct;

@Named("customerRepository")
public class CustomerRepositoryConfiguration implements CustomerApi {

    private final CustomerApi customerRepository;

    public CustomerRepositoryConfiguration() throws IOException {
        this.customerRepository = new com.merkleinc.interviewkata.repository.CustomerRepository();
    }

    @Override
    public Customer getCustomer(String customerId) throws RepositoryException {
        return this.customerRepository.getCustomer(customerId);
    }

    @Override
    public List<CustomerProduct> getCustomerProducts() {
        return this.customerRepository.getCustomerProducts();
    }

    @Override
    public List<CustomerProduct> getCustomerProducts(String accountNumber) {
        return this.customerRepository.getCustomerProducts(accountNumber);
    }
}
