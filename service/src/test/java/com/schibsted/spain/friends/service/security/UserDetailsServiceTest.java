package com.schibsted.spain.friends.service.security;

import com.schibsted.spain.friends.model.internal.user.UserApi;
import com.schibsted.spain.friends.model.internal.user.UserDetails;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    private static final String ANY_USERNAME = "username";
    private static final String ANY_PASSWORD = "password";

    @Mock
    private UserApi userApi;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void usernameNotFound() {
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(String.format("username %s not found", ANY_USERNAME));

        when(userApi.get(ANY_USERNAME)).thenReturn(Optional.empty());
        UserDetailsService testee = new UserDetailsService(userApi);

        testee.loadUserByUsername(ANY_USERNAME);
    }

    @Test
    public void UserDetailsReturnedCorrectly() {
        when(userApi.get(ANY_USERNAME)).thenReturn(Optional.of(new UserDetails(ANY_PASSWORD)));
        UserDetailsService testee = new UserDetailsService(userApi);

        org.springframework.security.core.userdetails.UserDetails userDetails = testee.loadUserByUsername(ANY_USERNAME);

        Assert.assertEquals(userDetails.getUsername(), ANY_USERNAME);
        Assert.assertNotNull(userDetails.getPassword());
        Assert.assertTrue(userDetails.getAuthorities().isEmpty());
    }

}