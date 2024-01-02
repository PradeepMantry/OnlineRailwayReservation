package com.casestudy.AdminOperationsMicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.casestudy.AdminOperationsMicroservice.configuration.UserInfoUserDetails;
import com.casestudy.AdminOperationsMicroservice.model.User;
import com.casestudy.AdminOperationsMicroservice.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	

	
	List<User> list = new ArrayList<>();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userInfo = 	userRepository.findByUserName(username);
	return 	userInfo.map(UserInfoUserDetails::new)
			.orElseThrow(()->new UsernameNotFoundException("user not found"));
	}
	
}
