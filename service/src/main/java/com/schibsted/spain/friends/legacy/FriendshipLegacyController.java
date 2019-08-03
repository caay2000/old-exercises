package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.api.FriendshipApi;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/friendship")
public class FriendshipLegacyController extends AbsctractLegacyController {

    private final FriendshipApi friendshipApi;

    @Inject
    public FriendshipLegacyController(FriendshipApi friendshipApi) {
        this.friendshipApi = friendshipApi;
    }


    @PostMapping("/request")
    void requestFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        friendshipApi.request(usernameFrom, usernameTo);
    }

    @PostMapping("/accept")
    void acceptFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        friendshipApi.accept(usernameFrom, usernameTo);
    }

    @PostMapping("/decline")
    void declineFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        friendshipApi.decline(usernameFrom, usernameTo);
    }

    @GetMapping("/list")
    Object listFriends(
            @RequestParam("username") String username,
            @RequestHeader("X-Password") String password
    ) {
        return friendshipApi.friends(username);
    }
}
