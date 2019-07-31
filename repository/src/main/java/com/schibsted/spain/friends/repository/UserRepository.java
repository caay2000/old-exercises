package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;

import javax.inject.Named;
import java.util.HashMap;

@Named("userRepository")
public class UserRepository implements UserApi {

    private final HashMap<String, String> users = new HashMap<>();

    @Override
    public User create(User user) {

        validate(user);

        users.put(user.getUsername(), user.getPassword());
        return user;
    }

    @Override
    public boolean isRegistered(String username) {
        return usernameExists(username);
    }

    @Override
    public User get(String username) {
        if (isRegistered(username)) {
            return new User(username, this.users.get(username));
        }
        throw new IllegalArgumentException(String.format("username %s does not exists", username));
    }

    private void validate(User user) {
        if (usernameExists(user.getUsername())) {
            throw new IllegalArgumentException("username already exists");
        }
    }

    private boolean usernameExists(String username) {
        return users.get(username) != null;
    }


}
