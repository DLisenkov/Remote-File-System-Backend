package com.project.security.details;

import com.project.models.State;
import com.project.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Class extends the {@link UserDetails} and sets the user's own characteristics
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * Filed user as {@link User} type
     */
    private User user;

    /**
     * Constructor - creating a new object with specific values
     *
     * @param user user object
     * @see UserDetailsImpl#user
     */
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    /**
     * Method of getting user
     *
     * @return user object
     * @see UserDetailsImpl#user
     */
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Method of getting hash password
     *
     * @return hash password
     */
    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    /**
     * Method of getting login
     *
     * @return login
     */
    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Method of checking if the account is banned
     *
     * @return returns false if account is banned
     */
    @Override
    public boolean isAccountNonLocked() {
        return !user.getState().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Method of checking if the account is active
     *
     * @return returns true if account is active
     */
    @Override
    public boolean isEnabled() {
        return user.getState().equals(State.ACTIVE);
    }
}
