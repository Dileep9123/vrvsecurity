package com.telusko.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller for handling basic HTTP requests such as "hello" and "about".
 * Provides simple endpoints to demonstrate session handling.
 */
@RestController
public class HelloController {

    /**
     * Handles requests to the "/hello" endpoint.
     * Returns a greeting message along with the session ID.
     *
     * @param request the HTTP request
     * @return a greeting message with the session ID
     */
    @GetMapping("hello")
    public String home(HttpServletRequest request) {
        return "Hello " + request.getSession().getId();
    }

    /**
     * Handles requests to the "/about" endpoint.
     * Returns an informational message along with the session ID.
     *
     * @param request the HTTP request
     * @return an informational message with the session ID
     */
    @GetMapping("about")
    public String about(HttpServletRequest request) {
        return "About " + request.getSession().getId();
    }
}
