package com.schibsted.spain.friends.model.api;

import com.schibsted.spain.friends.model.User;

public interface SignUpApi {

    User signUp(String username, String password);
}
