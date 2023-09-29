package com.tgsbhadohi.TGS.service.authorization;

import java.util.List;

import com.tgsbhadohi.TGS.entities.authorization.User;

public interface UserService {
	public List<User> createUser(User user);
	public List<User> getAllUser();
	public List<User> getUserByName(String email);
	
}
