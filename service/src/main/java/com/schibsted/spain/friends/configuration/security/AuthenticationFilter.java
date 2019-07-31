package com.schibsted.spain.friends.configuration.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named("authenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String username = getUsername((HttpServletRequest) request);
        String password = getPassword((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
        chain.doFilter(request, response);
    }

    private String getPassword(HttpServletRequest request) {
        return request.getHeader("X-Password");
    }

    private String getUsername(HttpServletRequest request) {
        return request.getQueryString().replace("username=", "");
    }

    @Override
    public void destroy() {

    }
}
