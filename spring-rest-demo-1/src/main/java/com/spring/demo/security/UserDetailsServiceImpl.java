package com.spring.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.demo.domain.User;
import com.spring.demo.exception.UsernameEmptyException;
import com.spring.demo.service.UserService;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.isEmpty(username)){
			throw new UsernameEmptyException();
		}
		User user = userService.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("User not found: "+username);
		}
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole())); 
		return userDetail;
	}

}
