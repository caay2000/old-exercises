package com.merkleinc.interviewkata.api.internal.customer.model;

import java.time.LocalDate;
public class Customer {

    private final String id;
    private final String accountNumber;
    private final String name;
    private final Gender gender;
    private final LocalDate birthday;
    private final String email;
    private final String phoneNumber;
    private final Address address;

    private Customer(String id,
                     String accountNumber,
                     String name,
                     Gender gender,
                     LocalDate birthday,
                     String email,
                     String phoneNumber,
                     Address address) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {

        private String id;
        private String accountNumber;
        private String name;
        private Gender gender;
        private LocalDate birthday;
        private String email;
        private String phoneNumber;
        private Address address;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Customer build() {
            return new Customer(id, accountNumber, name, gender, birthday, email, phoneNumber, address);
        }
    }
}
