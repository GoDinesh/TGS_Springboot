package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.PermissionGroup;

public interface PermissionGroupService {
	public List<PermissionGroup> getAllPermissionGroup();
	public List<PermissionGroup> savePermissionGroup(PermissionGroup permissionGroup);
}
