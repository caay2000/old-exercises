package com.schibsted.spain.friends.model.internal.user;

public class UserDetails {

    private final String password;

    public UserDetails(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
