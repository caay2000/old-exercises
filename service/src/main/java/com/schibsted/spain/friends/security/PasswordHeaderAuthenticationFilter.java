package com.schibsted.spain.friends.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

class PasswordHeaderAuthenticationFilter extends OncePerRequestFilter {

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