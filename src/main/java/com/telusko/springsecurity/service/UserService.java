package com.telusko.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.springsecurity.dao.UserRepo;
import com.telusko.springsecurity.model.User;

@Service
public class UserService {
	@Autowired
     UserRepo repo;
	
	public User save(User user) {
		return repo.save(user);
	}
}
