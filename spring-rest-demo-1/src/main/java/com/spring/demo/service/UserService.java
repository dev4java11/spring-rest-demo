package com.spring.demo.service;

import com.spring.demo.domain.User;

public interface UserService {

	public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username, String password);
}
