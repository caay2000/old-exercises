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

        return Customer.builder()
                .id(source.getId())
                .accountNumber(source.getAccountNumber())
                .name(source.getName())
                .gender(Gender.from(source.getGender()))
                .birthday(LocalDate.parse(source.getBirthday(), DateTimeFormatter.ofPattern("M/d/yyyy")))
                .email(source.getEmail())
                .phoneNumber(source.getPhoneNumber())
                .address(addressTranslator.translate(source.getAddress()))
                .build();
    }
}
