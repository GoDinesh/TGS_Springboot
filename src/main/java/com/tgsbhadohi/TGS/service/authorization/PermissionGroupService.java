package com.tgsbhadohi.TGS.service.authorization;

import java.util.List;

import com.tgsbhadohi.TGS.entities.authorization.PermissionGroup;

public interface PermissionGroupService {
	public List<PermissionGroup> getAllPermissionGroup();
	public List<PermissionGroup> savePermissionGroup(PermissionGroup permissionGroup);
}
