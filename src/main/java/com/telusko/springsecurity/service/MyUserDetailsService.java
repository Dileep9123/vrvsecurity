package com.telusko.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.springsecurity.dao.UserPrincipal;
import com.telusko.springsecurity.dao.UserRepo;
import com.telusko.springsecurity.model.User;

/**
 * Service implementation for loading user-specific data.
 * This class is responsible for retrieving user details from the database and 
 * converting them into a format compatible with Spring Security.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Loads a user by their username from the database.
     * Converts the retrieved user entity into a `UserDetails` object.
     *
     * @param username the username of the user to load
     * @return a `UserDetails` object containing user information
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user from the database
        User user = userRepo.findByUsername(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Wrap the User object in a UserPrincipal object and return it
        return new UserPrincipal(user);
    }
}
