package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.model.api.FriendshipApi;
import com.schibsted.spain.friends.model.internal.api.FriendApi;
import com.schibsted.spain.friends.model.internal.api.UserApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

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
        if(userApi.isRegistered(from) && userApi.isRegistered(to)){
            friendApi.request(from, to);
        } else {
            throw new ApplicationException("user not registered");
        }
    }

    @Override
    public void accept(String from, String to) {

    }

    @Override
    public void decline(String from, String to) {

    }

    @Override
    public List<String> friends(String username) {
        return new ArrayList<>();
    }
}
