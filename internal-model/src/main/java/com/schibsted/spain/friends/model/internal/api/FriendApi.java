package com.schibsted.spain.friends.model.internal.api;

import java.util.Set;

public interface FriendApi {

    Set<String> getAllRequests(String from);

    void addRequest(String from, String to);

    void removeRequest(String from, String to);

    Set<String> getAllFriends(String username);

    void makeFriends(String from, String to);
}
