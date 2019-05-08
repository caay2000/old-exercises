package com.merkleinc.interviewkata.repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merkleinc.interviewkata.repository.exception.RepositoryException;
import com.merkleinc.interviewkata.repository.model.Customer;
import com.merkleinc.interviewkata.repository.model.CustomerProduct;

public class CustomerRepository implements CustomerApi {

    private final List<Customer> customers;
    private final List<CustomerProduct> customerProducts;

    public CustomerRepository() throws IOException {

        this.customers = Collections.unmodifiableList(
                new ObjectMapper().readValue(
                        new InputStreamReader(CustomerRepository.class.
                                getResourceAsStream("/customer/customers.json")),
                        new TypeReference<List<Customer>>() {
                        }));

        this.customerProducts = Collections.unmodifiableList(
                new ObjectMapper().readValue(
                        new InputStreamReader(CustomerRepository.class.
                                getResourceAsStream("/customer/customerProducts.json")),
                        new TypeReference<List<CustomerProduct>>() {
                        }));
    }

    @Override
    public Customer getCustomer(String customerId) throws RepositoryException {
        return customers.stream()
                .filter(e -> customerId.equals(e.getId()))
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Customer not found"));
    }

    @Override
    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    @Override
    public List<CustomerProduct> getCustomerProducts(String accountNumber) {
        Customer customer = findCustomerByAccountNumber(accountNumber);
        return findProductsByCustomerId(customer.getId());
    }

    private Customer findCustomerByAccountNumber(String accountNumber) {
        for (Customer customer : customers) {
            if (customer.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    private List<CustomerProduct> findProductsByCustomerId(String id) {
        List<CustomerProduct> customerProducts = new ArrayList<>();
        for (CustomerProduct customerProduct : customerProducts) {
            if (customerProduct.getCustomerId().equalsIgnoreCase(id)) {
                customerProducts.add(customerProduct);
            }
        }
        return customerProducts;
    }
}
