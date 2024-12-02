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

@RestController
public class UserController {
    
	@Autowired
	UserService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("register")
	public User singUp(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return service.save(user);
	}
	
	@PostMapping("login")
	public String login(@RequestBody User user) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		else {
			return "Failure";
		}
		
	}
	
	
	
	
	
}
