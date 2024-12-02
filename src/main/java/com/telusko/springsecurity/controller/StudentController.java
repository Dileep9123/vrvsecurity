package com.telusko.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.springsecurity.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
   
	
	List<Student> list = new ArrayList<>(List.of(
			new Student(1, "Dileep", "Java"),
			new Student(2, "Bhavana", "Java")
			));
	
	
	
	@GetMapping("getall")
	public List<Student> getStudents() {
		return list;
	}
	
	@PostMapping("addstudent")
	public String addStudent(@RequestBody Student student) {
		list.add(student);
		return "Added Sucessfully";
	}
	
	@GetMapping("csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	
}

