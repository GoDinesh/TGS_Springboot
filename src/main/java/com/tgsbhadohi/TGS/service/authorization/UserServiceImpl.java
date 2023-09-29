package com.tgsbhadohi.TGS.service.authorization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.authorization.AssignPermissionDao;
import com.tgsbhadohi.TGS.dao.authorization.UserDao;
import com.tgsbhadohi.TGS.entities.authorization.AssignPermission;
import com.tgsbhadohi.TGS.entities.authorization.User;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AssignPermissionDao assignPermissionDao;
	
	
	
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
		User searchUser = userDao.findByEmail(email);
		if(searchUser!=null) {
			searchUser.setUserPermission(assignPermissionDao.findByGroupid(searchUser.getGroupid()));
		}
		user.add(searchUser);		
		return user;
	}
}

