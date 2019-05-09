package com.merkleinc.interviewkata.api.internal.customer.translator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.internal.customer.model.Customer;
import com.merkleinc.interviewkata.api.internal.customer.model.Gender;

public class CustomerTranslator implements Translator<com.merkleinc.interviewkata.repository.model.Customer, Customer> {

    private final AddressTranslator addressTranslator;

    public CustomerTranslator() {
        this.addressTranslator = new AddressTranslator();
    }

    @Override
    public Customer translate(com.merkleinc.interviewkata.repository.model.Customer source) {

        return new Customer.Builder()
                .withId(source.getId())
                .withAccountNumber(source.getAccountNumber())
                .withName(source.getName())
                .withGender(Gender.from(source.getGender()))
                .withBirthday(LocalDate.parse(source.getBirthday(), DateTimeFormatter.ofPattern("d/MM/yyyy")))
                .withEmail(source.getEmail())
                .withPhoneNumber(source.getPhoneNumber())
                .withAddress(addressTranslator.translate(source.getAddress()))
                .build();
    }
}
