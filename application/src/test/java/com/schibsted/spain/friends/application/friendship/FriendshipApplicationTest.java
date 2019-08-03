package com.schibsted.spain.friends.application.friendship;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.api.FriendshipApi;
import com.schibsted.spain.friends.model.internal.friend.FriendApi;
import com.schibsted.spain.friends.model.internal.user.UserApi;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipApplicationTest {

    private static final String ANY_USERNAME = "username";
    private static final String ANOTHER_USERNAME = "anotherUsername";

    @Mock
    private UserApi userApi;

    @Mock
    private FriendApi friendApi;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void requestToUnregisteredFails() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("user not registered");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(false);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.request(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void requestToOwnFails() {
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("cannot request friendship to yourself");

        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.request(ANY_USERNAME, ANY_USERNAME);
    }

    @Test
    public void requestToAlreadyRequestedFails() {
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("cannot request friendship twice");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        when(friendApi.getRequests(ANOTHER_USERNAME)).thenReturn(setOf(ANY_USERNAME));
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.request(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void requestToAlreadyFriendFails() {
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("cannot request friendship to someone that is already your friend");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        when(friendApi.getFriends(ANY_USERNAME)).thenReturn(setOf(ANOTHER_USERNAME));
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.request(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void requestWorks() {
        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.request(ANY_USERNAME, ANOTHER_USERNAME);

        verify(friendApi).addRequest(eq(ANY_USERNAME), eq(ANOTHER_USERNAME));
    }

    @Test
    public void acceptUnregisteredFails() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("user not registered");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(false);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.accept(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void acceptNotRequestedFails() {
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("cannot accept a request that does not exists");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.accept(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void acceptWorks() {
        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        when(friendApi.getRequests(ANY_USERNAME)).thenReturn(setOf(ANOTHER_USERNAME));
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.accept(ANY_USERNAME, ANOTHER_USERNAME);

        verify(friendApi).addFriend(eq(ANY_USERNAME), eq(ANOTHER_USERNAME));
        verify(friendApi).removeRequest(eq(ANY_USERNAME), eq(ANOTHER_USERNAME));
    }

    @Test
    public void declineUnregisteredFails() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("user not registered");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(false);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.decline(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void declineNotRequestedFails() {
        expectedException.expect(ApplicationException.class);
        expectedException.expectMessage("cannot decline a request that does not exists");

        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.decline(ANY_USERNAME, ANOTHER_USERNAME);
    }

    @Test
    public void declineWorks() {
        when(userApi.isRegistered(ANOTHER_USERNAME)).thenReturn(true);
        when(friendApi.getRequests(ANY_USERNAME)).thenReturn(setOf(ANOTHER_USERNAME));
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        testee.decline(ANY_USERNAME, ANOTHER_USERNAME);

        verify(friendApi).removeRequest(eq(ANY_USERNAME), eq(ANOTHER_USERNAME));
    }

    @Test
    public void getFriendsWorks() {
        Set<String> expectedResponse = setOf(ANOTHER_USERNAME);
        when(friendApi.getFriends(eq(ANY_USERNAME))).thenReturn(expectedResponse);
        FriendshipApi testee = new FriendshipApplication(userApi, friendApi);

        List<String> response = testee.friends(ANY_USERNAME);

        Assert.assertEquals(new ArrayList<>(expectedResponse), response);
    }

    private Set<String> setOf(String... values) {
        return new HashSet<>(Arrays.asList(values));
    }

}