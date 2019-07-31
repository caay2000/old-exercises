package com.schibsted.spain.friends.model.internal.api;

import java.util.List;

public interface FriendApi {

    void request(String from, String to);

    void accept(String from, String to);

    void decline(String from, String to);

    List<String> friends(String username);

}
