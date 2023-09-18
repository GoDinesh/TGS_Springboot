package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.AssignPermission;

public interface AssignPermissionService {

	public List<AssignPermission> getAllAssignPermission(); 
	public List<AssignPermission> saveAssignPermission(AssignPermission assignPermission);
	
}
