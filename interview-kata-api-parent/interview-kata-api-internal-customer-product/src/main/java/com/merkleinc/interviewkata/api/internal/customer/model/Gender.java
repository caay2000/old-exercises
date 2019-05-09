package com.merkleinc.interviewkata.api.internal.customer.model;

import java.util.Arrays;
public enum Gender {

    MALE("M"),
    FEMALE("F"),
    UNSPECIFIED("X");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender from(String value) {
        return Arrays.asList(Gender.values()).stream()
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(value + " not found on Gender enum"));
    }

    public boolean equals(String gender) {
        return this.value.equals(gender);
    }
}
