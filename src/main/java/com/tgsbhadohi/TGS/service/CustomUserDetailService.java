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
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// load user from database
//		try {
//			User user = userDao.findByEmail(username);
//			return user;
//		}catch(Exception ex) {
//			return null;
//		}
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userDao.findByEmail(username);
	    if (user == null) {
	        throw new UsernameNotFoundException("User not found");
	    }
	    return user;
	}
	

}
