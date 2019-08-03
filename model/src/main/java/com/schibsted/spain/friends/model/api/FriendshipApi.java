package com.schibsted.spain.friends.model.api;

import java.util.List;

public interface FriendshipApi {

    void request(String username, String to);

    void accept(String username, String requester);

    void decline(String username, String requester);

    List<String> friends(String username);
}
