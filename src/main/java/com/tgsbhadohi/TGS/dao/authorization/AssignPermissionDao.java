package com.tgsbhadohi.TGS.dao.authorization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.authorization.AssignPermission;

@Repository
public interface AssignPermissionDao extends JpaRepository<AssignPermission, Long>{

	public AssignPermission findByGroupid(String groupid);
}
