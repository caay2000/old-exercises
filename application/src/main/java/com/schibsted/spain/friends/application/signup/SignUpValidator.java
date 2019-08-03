package com.schibsted.spain.friends.application.signup;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.internal.user.UserApi;

import static java.util.Objects.isNull;

class SignUpValidator {

    private static final String ALPHANUMERIC_REGEX = "[A-Za-z0-9]+";
    private static final int USERNAME_MIN_SIZE = 5;
    private static final int USERNAME_MAX_SIZE = 10;
    private static final int PASSWORD_MIN_SIZE = 8;
    private static final int PASSWORD_MAX_SIZE = 12;

    private final UserApi userApi;

    public SignUpValidator(UserApi userApi) {
        this.userApi = userApi;
    }

    public void validate(String username, String password) {

        if (isNull(username)) {
            throw new IllegalArgumentException("username cannot be null");
        }

        if (username.length() < USERNAME_MIN_SIZE || username.length() > USERNAME_MAX_SIZE) {
            throw new IllegalArgumentException(String.format("username should have between %d and %d characters", USERNAME_MIN_SIZE, USERNAME_MAX_SIZE));
        }

        if (!username.matches(ALPHANUMERIC_REGEX)) {
            throw new IllegalArgumentException("username should be alphanumeric");
        }

        if (isNull(password)) {
            throw new IllegalArgumentException("password cannot be null");
        }

        if (password.length() < PASSWORD_MIN_SIZE || password.length() > PASSWORD_MAX_SIZE) {
            throw new IllegalArgumentException(String.format("password should have between %d and %d characters", PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE));
        }

        if (!password.matches(ALPHANUMERIC_REGEX)) {
            throw new IllegalArgumentException("password should be alphanumeric");
        }

        if (userApi.isRegistered(username)) {
            throw new ApplicationException(String.format("username %s already exists", username));
        }
    }
}
