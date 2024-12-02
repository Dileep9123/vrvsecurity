package com.telusko.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class HelloController {
	@GetMapping("hello")
	public String home(HttpServletRequest request) {
		return "Hello " + request.getSession().getId() ;
	}
	
	@GetMapping("about")
	public String about(HttpServletRequest request) {
		return "about " +  request.getSession().getId() ;
	}
}
