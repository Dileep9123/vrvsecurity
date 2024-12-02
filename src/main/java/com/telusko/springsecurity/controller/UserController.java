package com.telusko.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.springsecurity.model.User;
import com.telusko.springsecurity.service.JwtService;
import com.telusko.springsecurity.service.UserService;

/**
 * Controller for user-related operations such as registration and login.
 * Provides endpoints for signing up and authenticating users.
 */
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * Handles user registration.
     * Encodes the user's password and saves the user details.
     *
     * @param user the user details provided in the request body
     * @return the saved user
     */
    @PostMapping("register")
    public User signUp(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Encrypt the password
        return service.save(user); // Save the user in the database
    }

    /**
     * Handles user login.
     * Authenticates the user credentials and generates a JWT token if successful.
     *
     * @param user the user credentials provided in the request body
     * @return a JWT token if authentication is successful, or a failure message
     */
    @PostMapping("login")
    public String login(@RequestBody User user) {
        // Authenticate the user credentials
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        // Generate and return JWT token if authentication is successful
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Authentication failed. Please check your credentials.";
        }
    }
}
