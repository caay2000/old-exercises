package com.schibsted.spain.friends.configuration.security;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

@Named("userInformationService")
public class UserInformationService implements UserDetailsService {

    private final UserApi userApi;

    @Inject
    public UserInformationService(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApi.get(username);
        return new UserInformation(user.getUsername(), user.getPassword());
    }
}
