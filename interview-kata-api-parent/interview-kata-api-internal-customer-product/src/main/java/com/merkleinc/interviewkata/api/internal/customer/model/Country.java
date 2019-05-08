package com.merkleinc.interviewkata.api.internal.customer.model;

import java.util.Arrays;
public enum Country {

    SPAIN("+34"),
    UK("+44"),
    IRL("+353");

    private final String phonePrefix;

    Country(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public static Country from(String value) {
        return Arrays.asList(Country.values()).stream()
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(value + " not found on Country enum"));
    }
}
