package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpApplicationTest {

    //Username must be unique, from 5 to 10 alphanumeric characters.
    //Password from 8 to 12 alphanumeric characters.

    private static final String VALID_USERNAME = "123abc";
    private static final String VALID_PASSWORD = "1234abcd";

    private UserApi userApi = Mockito.mock(UserApi.class);

    @Test(expected = IllegalArgumentException.class)
    public void usernameNullFails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(null, VALID_PASSWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void usernameLessSize5Fails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("1234", VALID_PASSWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void usernameMoreSize10Fails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("12345678901", VALID_PASSWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void usernameNonAlphanumericFails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("123456*", VALID_PASSWORD);
    }

    @Test
    public void usernameWorks() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("1234abcd", VALID_PASSWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void passwordNullFails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void passwordLessSize8Fails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "1234567");
    }

    @Test(expected = IllegalArgumentException.class)
    public void passwordMoreSize12Fails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "1234567890123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void passwordNonAlphanumericFails() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "12345678*");
    }

    @Test
    public void userApiIsCalled() {

        when(userApi.create(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        SignUpApplication testee = new SignUpApplication(userApi);
        User user = testee.signUp(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertEquals(VALID_USERNAME, user.getUsername());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotEquals(VALID_PASSWORD, user.getPassword());

    }

}