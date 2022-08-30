package com.schibsted.spain.friends.application.signup;

import com.schibsted.spain.friends.model.api.SignUpApi;
import com.schibsted.spain.friends.model.internal.user.UserApi;

import javax.inject.Inject;
import javax.inject.Named;

@Named("signUpApplication")
public class SignUpApplication implements SignUpApi {

    private final UserApi userApi;
    private final SignUpValidator validator;

    @Inject
    public SignUpApplication(UserApi userApi) {
        this.userApi = userApi;
        this.validator = new SignUpValidator(userApi);
    }

    @Override
    public void signUp(String username, String password) {
        validator.validate(username, password);
        userApi.create(username, password);
    }
}
