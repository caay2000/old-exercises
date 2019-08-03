package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.friend.FriendApi;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

public class FriendRepositoryTest {

    private final static String ANY_USERNAME = "username";
    private final static String ANOTHER_USERNAME = "another";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getRequestWorks() {
        FriendApi testee = new FriendRepository();

        Set<String> requests = testee.getRequests(ANY_USERNAME);

        Assert.assertEquals(0, requests.size());
    }

    @Test
    public void addAndGetRequestWorks() {
        FriendApi testee = new FriendRepository();
        testee.addRequest(ANOTHER_USERNAME, ANY_USERNAME);

        Set<String> requests = testee.getRequests(ANY_USERNAME);

        Assert.assertEquals(1, requests.size());
        Assert.assertTrue(requests.contains(ANOTHER_USERNAME));
    }

    @Test
    public void removeRequestWorks() {
        FriendApi testee = new FriendRepository();
        testee.addRequest(ANOTHER_USERNAME, ANY_USERNAME);

        testee.removeRequest(ANY_USERNAME, ANOTHER_USERNAME);

        Set<String> requests = testee.getRequests(ANY_USERNAME);
        Assert.assertEquals(0, requests.size());
    }

    @Test
    public void getFriendsWorks() {
        FriendApi testee = new FriendRepository();

        Set<String> friends = testee.getFriends(ANY_USERNAME);

        Assert.assertEquals(0, friends.size());
    }

    @Test
    public void addAndGetFriendWorks() {
        FriendApi testee = new FriendRepository();

        testee.addFriend(ANY_USERNAME, ANOTHER_USERNAME);

        Set<String> friends = testee.getFriends(ANY_USERNAME);
        Assert.assertEquals(1, friends.size());
        Assert.assertTrue(friends.contains(ANOTHER_USERNAME));

        friends = testee.getFriends(ANOTHER_USERNAME);
        Assert.assertEquals(1, friends.size());
        Assert.assertTrue(friends.contains(ANY_USERNAME));
    }
}