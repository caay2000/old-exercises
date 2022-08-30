package com.schibsted.spain.friends.application.friendship;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.internal.friend.FriendApi;
import com.schibsted.spain.friends.model.internal.user.UserApi;

class FriendshipValidator {

    private final UserApi userApi;
    private final FriendApi friendApi;

    public FriendshipValidator(UserApi userApi, FriendApi friendApi) {
        this.userApi = userApi;
        this.friendApi = friendApi;
    }

    public void validateRequest(String username, String to) {
        if (username.equals(to)) {
            throw new ApplicationException("cannot request friendship to yourself");
        }
        if (!userApi.isRegistered(to)) {
            throw new IllegalArgumentException("user not registered");
        }
        if (friendApi.getRequests(to).contains(username)) {
            throw new ApplicationException("cannot request friendship twice");
        }
        if (friendApi.getFriends(username).contains(to)) {
            throw new ApplicationException("cannot request friendship to someone that is already your friend");
        }
    }

    public void validateAccept(String username, String requester) {
        if (!userApi.isRegistered(requester)) {
            throw new IllegalArgumentException("user not registered");
        }
        if (!friendApi.getRequests(username).contains(requester)) {
            throw new ApplicationException("cannot accept a request that does not exists");
        }
    }

    public void validateDecline(String username, String requester) {
        if (!userApi.isRegistered(requester)) {
            throw new IllegalArgumentException("user not registered");
        }
        if (!friendApi.getRequests(username).contains(requester)) {
            throw new ApplicationException("cannot decline a request that does not exists");
        }
    }
}
