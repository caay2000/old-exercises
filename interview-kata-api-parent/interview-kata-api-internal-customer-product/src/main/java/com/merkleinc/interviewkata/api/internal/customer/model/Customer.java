package com.merkleinc.interviewkata.api.internal.customer.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Customer {

    private final String id;
    private final String accountNumber;
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;
    private final String email;
    private final String phoneNumber;
    private final Address address;
}
