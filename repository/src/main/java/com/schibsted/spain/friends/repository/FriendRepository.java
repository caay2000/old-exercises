package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.api.FriendApi;

import javax.inject.Named;
import java.util.List;

@Named("friendRepository")
public class FriendRepository implements FriendApi {

    @Override
    public void request(String from, String to) {

    }

    @Override
    public void accept(String from, String to) {

    }

    @Override
    public void decline(String from, String to) {

    }

    @Override
    public List<String> friends(String username) {
        return null;
    }
}
