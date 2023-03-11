package com.project.security.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Personal token authentication implements {@link Authentication}
 */
public class TokenAuthentication implements Authentication {

    /**
     * Token value
     */
    private String token;

    /**
     * Authentication flag
     */
    private boolean isAuthenticated;

    /**
     * User details
     *
     * @see UserDetails
     */
    private UserDetails userDetails;

    /**
     * Constructor - creating a new object with specific values
     *
     * @param token token value
     * @see TokenAuthentication#token
     */
    public TokenAuthentication(String token) {
        this.token = token;
    }

    /**
     * Setting user details
     *
     * @param userDetails user details
     * @see TokenAuthentication#userDetails
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Getting user authorization
     *
     * @return authorization
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Getting user details
     *
     * @return user details
     * @see TokenAuthentication#userDetails
     */
    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    /**
     * Getting the authorization flag
     *
     * @return authorization flag
     * @see TokenAuthentication#isAuthenticated()
     */
    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Setting the authorization flag
     *
     * @param isAuthenticated authorization flag
     * @see TokenAuthentication#isAuthenticated()
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    /**
     * Getting token value
     *
     * @return token value
     * @see TokenAuthentication#token
     */
    @Override
    public String getName() {
        return token;
    }
}
