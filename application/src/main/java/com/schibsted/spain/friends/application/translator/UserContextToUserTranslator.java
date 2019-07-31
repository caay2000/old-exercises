package com.schibsted.spain.friends.application.translator;

import static java.util.Objects.isNull;

import com.schibsted.spain.friends.application.translator.context.UserContext;
import com.schibsted.spain.friends.common.Transformer;
import com.schibsted.spain.friends.common.Translator;
import com.schibsted.spain.friends.common.Validator;
import com.schibsted.spain.friends.model.User;

public class UserContextToUserTranslator extends Translator<UserContext, User> {

    public UserContextToUserTranslator() {
        super(new UserContextValidator(), new UserContextToUserTransformer());
    }

    private static class UserContextValidator implements Validator<UserContext> {

        private static final String ALPHANUMERIC_REGEX = "[A-Za-z0-9]+";
        private static final int USERNAME_MIN_SIZE = 5;
        private static final int USERNAME_MAX_SIZE = 10;
        private static final int PASSWORD_MIN_SIZE = 8;
        private static final int PASSWORD_MAX_SIZE = 12;

        @Override
        public void validate(UserContext context) {

            if (isNull(context.getUsername())) {
                throw new IllegalArgumentException("username cannot be null");
            }

            if (context.getUsername().length() < USERNAME_MIN_SIZE || context.getUsername().length() > USERNAME_MAX_SIZE) {
                throw new IllegalArgumentException(String.format("username should have between %d and %d characters", USERNAME_MIN_SIZE, USERNAME_MAX_SIZE));
            }

            if (!context.getUsername().matches(ALPHANUMERIC_REGEX)) {
                throw new IllegalArgumentException("username should be alphanumeric");
            }

            if (isNull(context.getPassword())) {
                throw new IllegalArgumentException("password cannot be null");
            }

            if (context.getPassword().length() < PASSWORD_MIN_SIZE || context.getPassword().length() > PASSWORD_MAX_SIZE) {
                throw new IllegalArgumentException(String.format("password should have between %d and %d characters", PASSWORD_MIN_SIZE, PASSWORD_MAX_SIZE));
            }

            if (!context.getPassword().matches(ALPHANUMERIC_REGEX)) {
                throw new IllegalArgumentException("password should be alphanumeric");
            }
        }
    }

    private static class UserContextToUserTransformer implements Transformer<UserContext, User> {

        @Override
        public User transform(UserContext element) {
            return null;
        }
    }

}
