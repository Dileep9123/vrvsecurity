package com.telusko.springsecurity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.springsecurity.dao.UserPrincipal;
import com.telusko.springsecurity.dao.UserRepo;
import com.telusko.springsecurity.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
	@Autowired
	UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = repo.findByUsername(username);
		
		if(user==null) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}
		
		return new UserPrincipal(user);
	}
	
	
}
