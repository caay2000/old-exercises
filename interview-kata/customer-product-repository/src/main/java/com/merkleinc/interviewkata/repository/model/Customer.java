package com.merkleinc.interviewkata.repository.model;

import java.util.StringJoiner;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private final String id;
    private final String name;
    private final String gender;
    private final String birthday;
    private final String email;
    private final String phoneNumber;
    private final String accountNumber;
    private final Address address;

    @JsonCreator
    public Customer(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("gender") String gender,
                    @JsonProperty("birthday") String birthday,
                    @JsonProperty("email") String email,
                    @JsonProperty("phoneNumber") String phoneNumber,
                    @JsonProperty("accountNumber") String accountNumber,
                    @JsonProperty("address") Address address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("gender='" + gender + "'")
                .add("birthday='" + birthday + "'")
                .add("email='" + email + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("accountNumber='" + accountNumber + "'")
                .add("address=" + address)
                .toString();
    }
}
