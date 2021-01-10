package com.webapp.spring.excersise;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepository.findUserByUserName(userName);
		if (user == null) {
			throw new RuntimeErrorException(null, "User is not found");
		}
		return new MyUserDetails(user);
	}
}