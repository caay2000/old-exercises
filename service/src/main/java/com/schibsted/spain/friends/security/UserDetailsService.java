package com.schibsted.spain.friends.security;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.user.UserApi;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserApi userApi;

    UserDetailsService(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.schibsted.spain.friends.model.internal.user.UserDetails> user = userApi.get(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("username %s not found", username));
        }

        UserDetails build = org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(createDelegatingPasswordEncoder().encode(user.get().getPassword()))
                .authorities(new ArrayList<>())
                .build();

        return build;
    }
}

