package com.schibsted.spain.friends.model.api;

import java.util.Set;

public interface FriendshipApi {

    void request(String from, String to);

    void accept(String from, String to);

    void decline(String from, String to);

    Set<String> friends(String username);
}
