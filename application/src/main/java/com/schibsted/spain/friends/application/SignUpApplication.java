package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.application.translator.UserContextToUserTranslator;
import com.schibsted.spain.friends.application.translator.context.UserContext;
import com.schibsted.spain.friends.common.Translator;
import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.api.SignUpApi;
import com.schibsted.spain.friends.model.internal.api.UserApi;

public class SignUpApplication implements SignUpApi {

    private final UserApi userApi;
    private final Translator<UserContext, User> userContextUserTranslator;

    public SignUpApplication(UserApi userApi) {
        this.userApi = userApi;
        userContextUserTranslator = new UserContextToUserTranslator();
    }

    @Override
    public User signUp(String username, String password) {

        return userApi.create(userContextUserTranslator.translate(new UserContext(username, password)));
    }
}
