package com.schibsted.spain.friends.model.internal.user;

import java.util.Optional;

public interface UserApi {

    void create(String username, String password);

    Optional<UserDetails> get(String username);

    boolean isRegistered(String username);


}
