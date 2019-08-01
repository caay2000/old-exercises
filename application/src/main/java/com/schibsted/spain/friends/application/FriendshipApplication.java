package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.model.api.FriendshipApi;
import com.schibsted.spain.friends.model.internal.api.FriendApi;
import com.schibsted.spain.friends.model.internal.api.UserApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named("friendshipApplication")
public class FriendshipApplication implements FriendshipApi {

    private final UserApi userApi;
    private final FriendApi friendApi;

    @Inject
    public FriendshipApplication(UserApi userApi, FriendApi friendApi) {
        this.userApi = userApi;
        this.friendApi = friendApi;
    }

    @Override
    public void request(String from, String to) {
        if (!userApi.isRegistered(from) || !userApi.isRegistered(to)) {
            throw new ApplicationException("user not registered");

        }
        if (from.equals(to)) {
            throw new ApplicationException("cannot request friendship to yourself");
        }
        Set<String> requests = friendApi.getAllRequests(from);
        if (requests.contains(to)) {
            throw new ApplicationException("cannot request friendship twice");
        }
        Set<String> friends = friendApi.getAllFriends(from);
        if (friends.contains(to)) {
            throw new ApplicationException("cannot request friendship to someone that is already your friend");
        }

        friendApi.addRequest(from, to);
    }

    @Override
    public void accept(String from, String to) {
        if (!userApi.isRegistered(from) || !userApi.isRegistered(to)) {
            throw new ApplicationException("user not registered");
        }
        Set<String> requests = friendApi.getAllRequests(to);
        if (requests.contains(from)) {
            friendApi.makeFriends(from, to);
            friendApi.makeFriends(to, from);
            friendApi.removeRequest(to, from);
        } else {
            throw new ApplicationException("cannot accept something that does not exists");
        }
    }

    @Override
    public void decline(String from, String to) {
        if (!userApi.isRegistered(from) || !userApi.isRegistered(to)) {
            throw new ApplicationException("user not registered");
        }
        Set<String> requests = friendApi.getAllRequests(to);
        if (requests.contains(from)) {
            friendApi.removeRequest(to, from);
        } else {
            throw new ApplicationException("cannot decline something that does not exists");
        }
    }

    @Override
    public Set<String> friends(String username) {
        return friendApi.getAllRequests(username);
    }
}
