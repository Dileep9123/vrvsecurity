package com.telusko.springsecurity.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.telusko.springsecurity.model.User;

/**
 * A custom implementation of {@link UserDetails} to represent the authenticated user.
 * This class bridges the application-specific {@link User} model with Spring Security.
 */
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final User user;

    /**
     * Constructs a new {@code UserPrincipal} for the given {@link User}.
     *
     * @param user the user entity
     */
    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Retrieves the collection of authorities granted to the user.
     *
     * @return a collection of granted authorities associated with the user's role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthorities();
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account is not expired.
     *
     * @return {@code true} as account expiration is not managed here
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is not locked.
     *
     * @return {@code true} as account locking is not managed here
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials are not expired.
     *
     * @return {@code true} as credential expiration is not managed here
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return {@code true} as account enabling is not managed here
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
