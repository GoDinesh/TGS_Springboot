package com.tgsbhadohi.TGS.dao.authorization;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.authorization.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}

