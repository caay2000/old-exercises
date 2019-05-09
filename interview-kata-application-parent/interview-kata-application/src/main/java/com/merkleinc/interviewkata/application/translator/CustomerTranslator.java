package com.merkleinc.interviewkata.application.translator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.internal.customer.model.Address;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;
import com.merkleinc.interviewkata.api.model.Customer;

public class CustomerTranslator implements Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> {

    @Override
    public Customer translate(com.merkleinc.interviewkata.api.internal.customer.model.Customer source) {
        return Customer.builder()
                .firstName(getFirstName(source.getName()))
                .middleName(getMiddleName(source.getName()))
                .lastName(getLastName(source.getName()))
                .gender(source.getGender().getValue())
                .birthDay(source.getBirthday().format(DateTimeFormatter.ofPattern("d MMMM uuuu")))
                .age(Integer.toString(Period.between(source.getBirthday(), LocalDate.now()).getYears()))
                .address(getAddress(source.getAddress()))
                .contactNumber(getPhoneNumber(source.getPhoneNumber(), source.getAddress().getCountry()))
                .contactEmail(source.getEmail())
                .build();
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

        List<String> nameSpplitted = Splitter.on(" ").splitToList(name);
        if (nameSpplitted.size() > 2) {
            return nameSpplitted.get(1);
        }
        return null;
    }

    private String getLastName(String name) {
        List<String> nameSplitted = Splitter.on(" ").splitToList(name);
        if (nameSplitted.size() > 2) {
            return Joiner.on(" ").join(nameSplitted.subList(2, nameSplitted.size()));
        }
        return nameSplitted.get(1);
    }
}
