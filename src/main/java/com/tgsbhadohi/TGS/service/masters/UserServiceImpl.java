package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.AssignPermissionDao;
import com.tgsbhadohi.TGS.dao.masters.UserDao;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.AssignPermission;
import com.tgsbhadohi.TGS.entities.masters.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public List<User> createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<User> data = new ArrayList<User>();
		data.add(userDao.save(user));
		return data;
	}
	
	public List<User> getAllUser() {
		List<User> data = new ArrayList<User>();
		data = userDao.findAll();
		for (User user : data) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return data;
	}
//
	@Override
	public List<User> getUserByName(String email) {
		List<User> user = new ArrayList<User>();
		user.add(userDao.findByEmail(email));
		return user;
	}
}

