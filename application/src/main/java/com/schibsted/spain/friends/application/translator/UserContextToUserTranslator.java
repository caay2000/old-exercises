package com.schibsted.spain.friends.application.translator;

import com.schibsted.spain.friends.application.translator.context.UserContext;
import com.schibsted.spain.friends.common.Transformer;
import com.schibsted.spain.friends.common.Translator;
import com.schibsted.spain.friends.model.User;

public class UserContextToUserTranslator extends Translator<UserContext, User> {

    public UserContextToUserTranslator() {
        super(new UserContextValidator(), new UserContextToUserTransformer());
    }

    private static class UserContextToUserTransformer implements Transformer<UserContext, User> {

        @Override
        public User transform(UserContext element) {
            return null;
        }
    }
}
