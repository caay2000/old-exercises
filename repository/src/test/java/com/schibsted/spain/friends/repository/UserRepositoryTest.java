package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.user.UserApi;
import com.schibsted.spain.friends.model.internal.user.UserDetails;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

public class UserRepositoryTest {

    private static final String ANY_USERNAME = "username";
    private static final String ANY_PASSWORD = "password";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createWorks() {

        UserApi testee = new UserRepository();

        testee.create(ANY_USERNAME, ANY_PASSWORD);
        Optional<UserDetails> userDetails = testee.get(ANY_USERNAME);

        Assert.assertTrue(userDetails.isPresent());
        Assert.assertEquals(ANY_PASSWORD, userDetails.get().getPassword());
    }

    @Test
    public void createDuplicated() {

        UserApi testee = new UserRepository();
        testee.create(ANY_USERNAME, ANY_PASSWORD);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(String.format("username %s already exists", ANY_USERNAME));

        testee.create(ANY_USERNAME, ANY_PASSWORD);
    }

    @Test
    public void isRegisteredTrue() {

        UserApi testee = new UserRepository();
        testee.create(ANY_USERNAME, ANY_PASSWORD);

        boolean isRegistered = testee.isRegistered(ANY_USERNAME);

        Assert.assertTrue(isRegistered);
    }

    @Test
    public void isRegisteredFalse() {
        UserApi testee = new UserRepository();

        boolean isRegistered = testee.isRegistered(ANY_USERNAME);

        Assert.assertFalse(isRegistered);
    }

    @Test
    public void getExistingUser() {
        UserApi testee = new UserRepository();
        testee.create(ANY_USERNAME, ANY_PASSWORD);

        Optional<UserDetails> userDetails = testee.get(ANY_USERNAME);

        Assert.assertEquals(ANY_PASSWORD, userDetails.get().getPassword());
    }

    @Test
    public void getNonExistingUser() {
        UserApi testee = new UserRepository();

        Optional<UserDetails> userDetails = testee.get(ANY_USERNAME);

        Assert.assertFalse(userDetails.isPresent());
    }
}
