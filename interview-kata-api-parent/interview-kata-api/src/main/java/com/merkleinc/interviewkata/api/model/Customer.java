package com.merkleinc.interviewkata.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

    private final String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String gender;
    private final String birthDay;
    private final String age;
    private final String address;
    private final String contactNumber;
    private final String contactEmail;
}
