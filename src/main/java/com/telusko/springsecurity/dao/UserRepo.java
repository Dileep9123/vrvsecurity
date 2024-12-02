package com.telusko.springsecurity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.springsecurity.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
        
	User findByUsername(String username);
}
 