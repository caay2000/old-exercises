package com.schibsted.spain.friends.model.internal.api;

import com.schibsted.spain.friends.model.User;

public interface UserApi {

    User create(User user);

    boolean isRegistered(String username);

    User get(String username);
}
