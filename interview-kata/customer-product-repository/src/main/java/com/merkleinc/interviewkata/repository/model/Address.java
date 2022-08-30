package com.merkleinc.interviewkata.repository.model;

import java.util.StringJoiner;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Address {

    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String postCode;
    private final String country;

    @JsonCreator
    public Address(@JsonProperty("addressLine1") String addressLine1,
                   @JsonProperty("addressLine2") String addressLine2,
                   @JsonProperty("city") String city,
                   @JsonProperty("postCode") String postCode,
                   @JsonProperty("country") String country) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("addressLine1='" + addressLine1 + "'")
                .add("addressLine2='" + addressLine2 + "'")
                .add("city='" + city + "'")
                .add("postCode='" + postCode + "'")
                .add("country='" + country + "'")
                .toString();
    }
}
