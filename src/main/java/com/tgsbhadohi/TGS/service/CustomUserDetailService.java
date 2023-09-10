package com.tgsbhadohi.TGS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.UserDao;
import com.tgsbhadohi.TGS.entities.masters.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user from database
		User user = userDao.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found !!"));
		return user;
	}
	

}
