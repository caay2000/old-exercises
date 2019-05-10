package com.merkleinc.interviewkata.application;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.CustomerApi;
import com.merkleinc.interviewkata.api.internal.customer.CustomerInternalApi;
import com.merkleinc.interviewkata.api.model.Customer;
import com.merkleinc.interviewkata.application.exception.NotFoundException;
import com.merkleinc.interviewkata.application.translator.CustomerTranslator;

@Named("customerApplication")
public class CustomerApplication implements CustomerApi {

    private final CustomerInternalApi customerInternalApi;
    private final Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> customerTranslator;

    @Inject
    public CustomerApplication(@Named("customerAdapter") CustomerInternalApi customerInternalApi) {

        this.customerInternalApi = customerInternalApi;
        this.customerTranslator = new CustomerTranslator();
    }

    @Override
    public Customer get(String id) {
        Optional<com.merkleinc.interviewkata.api.internal.customer.model.Customer> customer = customerInternalApi.getCustomer(id);
        return customer.map(customerTranslator::translate)
                .orElseThrow(() -> new NotFoundException("customer " + id + " not found"));
    }
}
