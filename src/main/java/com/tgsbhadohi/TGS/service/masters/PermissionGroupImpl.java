package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.PermissionGroupDao;
import com.tgsbhadohi.TGS.entities.masters.PermissionGroup;

@Service
public class PermissionGroupImpl implements PermissionGroupService {
	
	@Autowired
	private PermissionGroupDao permissionGroupDao;
	
	@Override
	public List<PermissionGroup> savePermissionGroup(PermissionGroup permissionGroup) {
		List<PermissionGroup> data = new ArrayList<PermissionGroup>();
		data.add(permissionGroupDao.save(permissionGroup));
		return data;	
	}

	@Override
	public List<PermissionGroup> getAllPermissionGroup() {
		return permissionGroupDao.findAll();
	}
}
