package com.project.security.filters;

import com.project.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class that filters a request for a token extends {@link Filter}
 */
@Component
public class TokenAuthFilter implements Filter {

    /**
     * Method gets the token from the request and creates authentication {@link TokenAuthentication}
     *
     * @param servletRequest  request data
     * @param servletResponse response data
     * @param filterChain     filter chain for passing data to the next filter
     * @throws IOException      if an error occurred while passing to the next filter
     * @throws ServletException if an error occurred while passing to the next filter
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = request.getHeader("authorization");
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);

        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
