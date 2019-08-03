package com.schibsted.spain.friends.security;

import com.schibsted.spain.friends.model.internal.user.UserApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    private static class MvcSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public MvcSecurityConfigurationAdapter() {
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
    private static class MvcProtectedSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private final UserApi userApi;

        public MvcProtectedSecurityConfigurerAdapter(UserApi userApi) {
            this.userApi = userApi;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .addFilterBefore(new PasswordHeaderAuthenticationFilter(), BasicAuthenticationFilter.class)
                    .userDetailsService(new UserDetailsService(userApi))
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf()
                    .disable();
        }
    }
}
