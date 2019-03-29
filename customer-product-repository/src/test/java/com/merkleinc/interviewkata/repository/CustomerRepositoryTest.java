package com.merkleinc.interviewkata.repository;

import com.merkleinc.interviewkata.repository.exception.RepositoryException;
import com.merkleinc.interviewkata.repository.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomerRepositoryTest {

    private CustomerRepository testee;

    @Before
    public void setUp() throws IOException {

        testee = new CustomerRepository();
    }

    @Test
    public void getCustomer_ReturnsCustomer() throws Exception {

        Customer customer = testee.getCustomer("1");

        Assert.assertNotNull(customer);
    }

    @Test
    public void getCustomer_ReturnsException_WhenCustomerNotFound() {

        assertThatThrownBy(() -> testee.getCustomer("notFound"))
                .isInstanceOf(RepositoryException.class)
                .hasMessageContaining("Customer not found");
    }
}
