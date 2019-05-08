package com.merkleinc.interviewkata.api.internal.customer.model;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
public class Address {

    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String postCode;
    private final Country country;

    private Address(String addressLine1, String addressLine2, String city, String postCode, Country country) {
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

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postCode, address.postCode) &&
                country == address.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, city, postCode, country);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressLine1", addressLine1)
                .append("addressLine2", addressLine2)
                .append("city", city)
                .append("postCode", postCode)
                .append("country", country)
                .toString();
    }

    public static class Builder {

        private String addressLine1;
        private String addressLine2;
        private String city;
        private String postCode;
        private Country country;

        public Builder withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder withCountry(Country country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(addressLine1, addressLine2, city, postCode, country);
        }
    }
}
