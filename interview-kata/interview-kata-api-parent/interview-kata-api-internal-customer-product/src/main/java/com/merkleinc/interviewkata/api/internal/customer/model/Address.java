package com.merkleinc.interviewkata.api.internal.customer.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Address {

    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String postCode;
    private final Country country;
}
