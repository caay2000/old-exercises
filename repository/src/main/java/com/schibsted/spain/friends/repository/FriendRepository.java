package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.model.internal.api.FriendApi;

import javax.inject.Named;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

@Named("friendRepository")
public class FriendRepository implements FriendApi {

    private Map<String, Set<String>> requests = new HashMap<>();
    private Map<String, Set<String>> friends = new HashMap<>();

    @Override
    public Set<String> getAllRequests(String from) {
        Set<String> requests = this.requests.get(from);
        if (isNull(requests)) {
            requests = new HashSet<>();
            this.requests.put(from, requests);
        }
        return requests;
    }

    @Override
    public void addRequest(String from, String to) {
        this.getAllRequests(from).add(to);
    }

    @Override
    public void removeRequest(String from, String to) {
        Set<String> requests = this.getAllRequests(from);
        requests.remove(to);
    }

    @Override
    public void makeFriends(String from, String to) {
        Set<String> friends = this.getAllFriends(from);
        friends.add(to);
    }

    @Override
    public Set<String> getAllFriends(String from) {
        Set<String> friends = this.friends.get(from);
        if (isNull(friends)) {
            friends = new HashSet<>();
            this.friends.put(from, friends);
        }
        return friends;
    }
}
