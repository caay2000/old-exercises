package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.user.UserApi;
import com.schibsted.spain.friends.model.internal.user.UserDetails;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Optional;

@Named("userRepository")
public class UserRepository implements UserApi {

    private final HashMap<String, String> users = new HashMap<>();

    @Override
    public void create(String username, String password) {

        if (isRegistered(username)) {
            throw new IllegalArgumentException(String.format("username %s already exists", username));
        }
        users.put(username, password);
    }

    @Override
    public Optional<UserDetails> get(String username) {
        if (isRegistered(username)) {
            return Optional.of(new UserDetails(users.get(username)));
        }
        return Optional.empty();
    }

    @Override
    public boolean isRegistered(String username) {
        return users.get(username) != null;
    }

}
