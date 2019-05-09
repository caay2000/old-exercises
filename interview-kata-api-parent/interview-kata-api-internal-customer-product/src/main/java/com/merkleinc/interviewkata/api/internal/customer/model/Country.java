package com.merkleinc.interviewkata.api.internal.customer.model;

import java.util.Arrays;
public enum Country {

    SPAIN("Spain", "+34"),
    UK("United Kingdom", "+44"),
    IRL("Ireland", "+353");

    private final String value;
    private final String phonePrefix;

    Country(String value, String phonePrefix) {

        this.value = value;
        this.phonePrefix = phonePrefix;
    }

    public String getValue() {
        return this.value;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public static Country from(String value) {
        return Arrays.asList(Country.values()).stream()
                .filter(e -> e.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(value + " not found on Country enum"));
    }
}
