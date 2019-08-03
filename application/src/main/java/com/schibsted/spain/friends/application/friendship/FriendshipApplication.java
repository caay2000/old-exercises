package com.schibsted.spain.friends.application.friendship;

import com.schibsted.spain.friends.model.api.FriendshipApi;
import com.schibsted.spain.friends.model.internal.friend.FriendApi;
import com.schibsted.spain.friends.model.internal.user.UserApi;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("friendshipApplication")
public class FriendshipApplication implements FriendshipApi {

    private final UserApi userApi;
    private final FriendApi friendApi;
    private final FriendshipValidator validator;

    @Inject
    public FriendshipApplication(UserApi userApi, FriendApi friendApi) {
        this.userApi = userApi;
        this.friendApi = friendApi;
        this.validator = new FriendshipValidator(userApi, friendApi);
    }


    @Override
    public void request(String username, String to) {
        validator.validateRequest(username, to);

        friendApi.addRequest(username, to);
    }


    @Override
    public void accept(String username, String requester) {
        validator.validateAccept(username, requester);

        friendApi.addFriend(username, requester);
        friendApi.removeRequest(username, requester);
    }

    @Override
    public void decline(String username, String requester) {
        validator.validateDecline(username, requester);

        friendApi.removeRequest(username, requester);
    }

    @Override
    public List<String> friends(String username) {
        return friendApi.getFriends(username).stream()
                .sorted(FriendshipApplication::reverseComparator)
                .collect(Collectors.toList());
    }

    private static int reverseComparator(String o1, String o2) {
        return o1.compareTo(o2) * -1;
    }
}
