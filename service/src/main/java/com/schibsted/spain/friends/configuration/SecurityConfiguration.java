package com.schibsted.spain.friends.configuration;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.model.internal.api.UserApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.util.Objects.isNull;

@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    private static class NoSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public NoSecurityConfigurationAdapter() {
        }

        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/signup")
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }
    }

    @Configuration
    private static class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private final UserApi userApi;

        public SecurityConfigurerAdapter(UserApi userApi) {
            this.userApi = userApi;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .addFilterBefore(new PasswordHeaderAuthenticationFilter(), BasicAuthenticationFilter.class)
                    .userDetailsService(new FriendsAppUserDetailsService(userApi))
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf()
                    .disable();
        }
    }

    private static class FriendsAppUserDetailsService implements UserDetailsService {

        private final UserApi userApi;

        public FriendsAppUserDetailsService(UserApi userApi) {
            this.userApi = userApi;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userApi.get(username);

            UserDetails build = org.springframework.security.core.userdetails.User.builder()
                    .passwordEncoder(e -> {
                        MessageDigest md5 = null;
                        try {
                            md5 = MessageDigest.getInstance("MD5");
                        } catch (NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                        }
                        return new String(md5.digest(e.getBytes()));
                    })
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(new ArrayList<>())
                    .build();

            return build;
        }
    }

    private static class PasswordHeaderAuthenticationFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

            String username = getUsername(request);
            String password = getPassword(request);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
            chain.doFilter(request, response);
        }

        private String getPassword(HttpServletRequest request) {
            return request.getHeader("X-Password");
        }

        private String getUsername(HttpServletRequest request) {
            String username = request.getParameter("username");
            if (isNull(username)) {
                username = request.getParameter("usernameFrom");
            }
            return username;
        }

    }


}
