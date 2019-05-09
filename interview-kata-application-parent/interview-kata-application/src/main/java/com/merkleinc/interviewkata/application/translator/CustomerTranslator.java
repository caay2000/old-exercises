package com.merkleinc.interviewkata.application.translator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import com.google.common.base.Splitter;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.internal.customer.model.Address;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;
import com.merkleinc.interviewkata.api.model.Customer;

public class CustomerTranslator implements Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> {

    @Override
    public Customer translate(com.merkleinc.interviewkata.api.internal.customer.model.Customer source) {
        Customer customer = new Customer();
        customer.setFirstName(getFirstName(source.getName()));
        customer.setMiddleName(getMiddleName(source.getName()));
        customer.setLastName(getLastName(source.getName()));
        customer.setGender(source.getGender().getValue());
        customer.setBirthDay(source.getBirthday().format(DateTimeFormatter.ofPattern("d MMMM uuuu")));
        customer.setAge(Integer.toString(Period.between(source.getBirthday(), LocalDate.now()).getYears()));
        customer.setAddress(getAddress(source.getAddress()));
        customer.setContactNumber(getPhoneNumber(source.getPhoneNumber(), source.getAddress().getCountry()));
        customer.setContactEmail(source.getEmail());
        return customer;
    }

    private String getPhoneNumber(String phoneNumber, Country country) {
        return new StringBuilder()
                .append(country.getPhonePrefix())
                .append(" ")
                .append(phoneNumber.replaceAll("-", " "))
                .toString();
    }

    private String getAddress(Address address) {
        return new StringBuilder()
                .append(address.getAddressLine1())
                .append(StringUtils.isNotEmpty(address.getAddressLine2()) ? " " + address.getAddressLine2() : "")
                .append(" ")
                .append(address.getPostCode())
                .append("-")
                .append(address.getCity())
                .append(" (")
                .append(address.getCountry().getValue())
                .append(")")
                .toString();
    }

    private String getFirstName(String name) {
        return Splitter.on(" ").splitToList(name).get(0);
    }

    private String getMiddleName(String name) {
        return null;
    }

    private String getLastName(String name) {
        return Splitter.on(" ").splitToList(name).get(1);
    }
}
