package com.schibsted.spain.friends.repository;

import java.util.HashMap;
import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;

public class UserRepository implements UserApi {

    private final HashMap<String, String> users = new HashMap<>();

    @Override
    public User create(User user) {

        if (usernameExists(user)) {
            throw new IllegalArgumentException("usenamen already exists");
        }

        users.put(user.getUsername(), user.getPassword());
        return user;
    }

    private boolean usernameExists(User user) {
        return users.keySet().stream().anyMatch(username -> username.equals(user.getUsername()));
    }
}
