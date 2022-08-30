package com.schibsted.spain.friends.model.internal.friend;

import java.util.Set;

public interface FriendApi {

    Set<String> getRequests(String from);

    Set<String> getFriends(String username);

    void addRequest(String from, String to);

    void removeRequest(String from, String to);

    void addFriend(String from, String to);
}
