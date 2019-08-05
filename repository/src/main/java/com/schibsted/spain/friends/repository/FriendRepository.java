package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.friend.FriendApi;

import javax.inject.Named;
import java.util.*;

import static java.util.Objects.isNull;

@Named("friendRepository")
public class FriendRepository implements FriendApi {

    private final Map<String, Set<String>> requests = new HashMap<>();
    private final Map<String, Set<String>> friends = new HashMap<>();

    @Override
    public Set<String> getRequests(String user) {
        return get(user, requests);
    }

    @Override
    public Set<String> getFriends(String user) {
        return get(user, friends);
    }

    @Override
    public void addRequest(String user, String to) {
        getRequests(to).add(user);
    }

    @Override
    public void removeRequest(String user, String to) {
        getRequests(user).remove(to);
    }

    @Override
    public void addFriend(String user, String to) {
        getFriends(user).add(to);
        getFriends(to).add(user);
    }

    private Set<String> get(String value, Map<String, Set<String>> map) {
        Set<String> set = map.get(value);
        if (isNull(set)) {
            set = new LinkedHashSet<>();
            map.put(value, set);
        }
        return set;
    }
}
