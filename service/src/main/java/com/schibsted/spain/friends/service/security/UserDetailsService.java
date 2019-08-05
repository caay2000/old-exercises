package com.schibsted.spain.friends.service.security;

import com.schibsted.spain.friends.model.internal.user.UserApi;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserApi userApi;
    private final PasswordEncoder encoder;

    UserDetailsService(UserApi userApi) {
        this.userApi = userApi;
        this.encoder = createDelegatingPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.schibsted.spain.friends.model.internal.user.UserDetails> user = userApi.get(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("username %s not found", username));
        }

        UserDetails build = org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(encoder.encode(user.get().getPassword()))
                .authorities(new ArrayList<>())
                .build();

        return build;
    }
}

