package com.merkleinc.interviewkata.application.translator;

import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.model.Customer;

public class CustomerTranslator implements Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> {

    @Override
    public Customer translate(com.merkleinc.interviewkata.api.internal.customer.model.Customer source) {
        Customer customer = new Customer();
        customer.setFirstName(source.getName());
        customer.setMiddleName(source.getName());
        customer.setLastName(source.getName());
        customer.setGender(source.getGender().toString());
        customer.setBirthDay(source.getBirthday().toString());
        customer.setAge(("age"));
        customer.setAddress(source.getAddress().toString());
        customer.setContactNumber(source.getPhoneNumber());
        customer.setContactEmail(source.getEmail());
        return customer;
    }
}
