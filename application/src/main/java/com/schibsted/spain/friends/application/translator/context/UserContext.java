package com.schibsted.spain.friends.application.translator.context;

public class UserContext {

    private final String username;
    private final String password;

    public UserContext(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
