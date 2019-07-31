package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserRepositoryTest {

    private static final User ANY_USER = new User("user", "password");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createWorks() {

        UserApi testee = new UserRepository();

        User user = testee.create(ANY_USER);

        Assert.assertEquals(ANY_USER, user);
    }

    @Test
    public void usernameAlreadyExists() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("username already exists");

        UserApi testee = new UserRepository();

        testee.create(ANY_USER);
        testee.create(ANY_USER);
    }

    @Test
    public void isRegisteredWorks() {

        UserApi testee = new UserRepository();
        testee.create(ANY_USER);

        boolean isRegistered = testee.isRegistered(ANY_USER.getUsername());

        Assert.assertTrue(isRegistered);
    }
}
