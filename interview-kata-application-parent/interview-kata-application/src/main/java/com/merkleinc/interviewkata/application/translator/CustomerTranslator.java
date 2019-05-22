package com.merkleinc.interviewkata.application.translator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.internal.customer.model.Address;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;
import com.merkleinc.interviewkata.api.model.Customer;

public class CustomerTranslator implements Translator<com.merkleinc.interviewkata.api.internal.customer.model.Customer, Customer> {

    private static final String DATE_PATTERN = "d MMMM uuuu";

    @Override
    public Customer translate(com.merkleinc.interviewkata.api.internal.customer.model.Customer source) {
        NameProvider nameProvider = new NameProvider(source.getName());
        return Customer.builder()
                .firstName(nameProvider.firstName)
                .middleName(nameProvider.middleName)
                .lastName(nameProvider.lastName)
                .gender(source.getGender().getValue())
                .birthday(getBirthday(source.getBirthday()))
                .age(Integer.toString(Period.between(source.getBirthday(), LocalDate.now()).getYears()))
                .address(getAddress(source.getAddress()))
                .contactNumber(getPhoneNumber(source.getPhoneNumber(), source.getAddress().getCountry()))
                .contactEmail(source.getEmail())
                .build();
    }

    private String getBirthday(LocalDate birthday) {
        return birthday.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private String getPhoneNumber(String phoneNumber, Country country) {
        return String.format("%s %s", country.getPhonePrefix(), phoneNumber.replaceAll("-", " "));
    }

    private String getAddress(Address address) {

        String addressLine = Stream.of(address.getAddressLine1(), address.getAddressLine2())
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(" "));
        return String.format("%s %s-%s (%s)", addressLine, address.getPostCode(), address.getCity(), address.getCountry().getValue());
    }

    private static class NameProvider {

        final String firstName;
        final String middleName;
        final String lastName;

        NameProvider(String name) {
            String[] split = name.split(" ", 3);
            this.firstName = split[0];
            if (split.length == 2) {
                this.middleName = null;
                this.lastName = split[1];
            } else {
                this.middleName = split[1];
                this.lastName = split[2];
            }
        }
    }
}
