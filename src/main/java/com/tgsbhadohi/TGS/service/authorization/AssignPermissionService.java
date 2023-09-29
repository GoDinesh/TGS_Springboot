package com.tgsbhadohi.TGS.service.authorization;

import java.util.List;

import com.tgsbhadohi.TGS.entities.authorization.AssignPermission;

public interface AssignPermissionService {

	public List<AssignPermission> getAllAssignPermission(); 
	public List<AssignPermission> saveAssignPermission(AssignPermission assignPermission);
	public List<AssignPermission> getAssignPermissionById(String groupId);	
}
