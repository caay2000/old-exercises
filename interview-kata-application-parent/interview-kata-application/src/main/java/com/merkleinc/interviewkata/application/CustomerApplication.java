package com.merkleinc.interviewkata.application;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.CustomerApi;
import com.merkleinc.interviewkata.api.model.Customer;
import com.merkleinc.interviewkata.application.translator.CustomerTranslator;

@Named("customerApplication")
public class CustomerApplication implements CustomerApi {

    private final com.merkleinc.interviewkata.api.internal.customer.CustomerApi customerApi;
    private final Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> customerTranslator;

    @Inject
    public CustomerApplication(com.merkleinc.interviewkata.api.internal.customer.CustomerApi customerApi) {

        this.customerApi = customerApi;
        this.customerTranslator = new CustomerTranslator();
    }

    @Override
    public Customer get(String id) {
        Optional<com.merkleinc.interviewkata.api.internal.customer.model.Customer> customer = customerApi.getCustomer(id);
        if (customer.isPresent()) {
            return customerTranslator.translate(customer.get());
        }
        return null;
    }
}
