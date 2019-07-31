package com.schibsted.spain.friends.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@EnableWebSecurity
public class SecurityConfiguration {



    @Configuration
    public static class HttpSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Inject
        private final Filter authenticationFilter;

        public HttpSecurityConfigurationAdapter(Filter authenticationFilter) {
            this.authenticationFilter = authenticationFilter;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/signup").permitAll()
                    .anyRequest().authenticated();

            http.addFilterAt(authenticationFilter, BasicAuthenticationFilter.class);
        }
    }
}
