package com.project.security.filters;

import com.project.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)servletRequest;

//        String token = "";
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie : cookies) {
//            if(cookie.getName().equals("Auth-Token")) {
//                token = cookie.getValue();
//            }
//        }
//        System.out.println(token);

        String token = request.getParameter("token");
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);

        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
