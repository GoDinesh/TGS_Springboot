package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.User;

public interface UserService {
	public List<User> createUser(User user);
	public List<User> getAllUser();
	public List<User> getUserByName(String email);
	
}
