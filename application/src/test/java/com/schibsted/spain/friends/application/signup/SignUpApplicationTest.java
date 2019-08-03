package com.schibsted.spain.friends.application.signup;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.internal.user.UserApi;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignUpApplicationTest {

    //Username must be unique, from 5 to 10 alphanumeric characters.
    //Password from 8 to 12 alphanumeric characters.

    private static final String VALID_USERNAME = "123ab";
    private static final String VALID_PASSWORD = "1234abcd";

    @Mock
    private UserApi userApi;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void usernameNullFails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("username cannot be null");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(null, VALID_PASSWORD);
    }

    @Test
    public void usernameSizeLessThan5Fails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("username should have between 5 and 10 characters");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("1234", VALID_PASSWORD);
    }

    @Test
    public void usernameSizeMoreThan10Fails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("username should have between 5 and 10 characters");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("12345678901", VALID_PASSWORD);
    }

    @Test
    public void usernameNonAlphanumericFails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("username should be alphanumeric");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("123456*", VALID_PASSWORD);
    }

    @Test
    public void usernameWorks() {

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp("1234abcd", VALID_PASSWORD);
    }

    @Test
    public void passwordNullFails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("password cannot be null");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, null);
    }

    @Test
    public void passwordSizeLessThan8Fails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("password should have between 8 and 12 characters");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "1234567");
    }

    @Test
    public void passwordSizeMoreThan12Fails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("password should have between 8 and 12 characters");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "1234567890123");
    }

    @Test
    public void passwordNonAlphanumericFails() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("password should be alphanumeric");

        SignUpApplication testee = new SignUpApplication(userApi);

        testee.signUp(VALID_USERNAME, "12345678*");
    }

    @Test
    public void alreadyRegisteredUserThrowsException() {

        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage(String.format("username %s already exists", VALID_USERNAME));

        SignUpApplication testee = new SignUpApplication(userApi);
        when(userApi.isRegistered(VALID_USERNAME)).thenReturn(true);

        testee.signUp(VALID_USERNAME, VALID_PASSWORD);
    }

    @Test
    public void userApiIsCalled() {

        SignUpApplication testee = new SignUpApplication(userApi);
        testee.signUp(VALID_USERNAME, VALID_PASSWORD);

        verify(userApi).create(eq(VALID_USERNAME), eq(VALID_PASSWORD));
    }
}