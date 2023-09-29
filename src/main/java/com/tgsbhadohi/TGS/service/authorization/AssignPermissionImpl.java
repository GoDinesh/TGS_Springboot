package com.tgsbhadohi.TGS.service.authorization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.authorization.AssignPermissionDao;
import com.tgsbhadohi.TGS.entities.authorization.AssignPermission;

@Service
public class AssignPermissionImpl implements AssignPermissionService {

	@Autowired
	private AssignPermissionDao assignPermissionDao;
	
	@Override
	public List<AssignPermission> getAllAssignPermission() {
		return assignPermissionDao.findAll();
	}

	@Override
	public List<AssignPermission> saveAssignPermission(AssignPermission assignPermission) {
		List<AssignPermission> data = new ArrayList<AssignPermission>();
		data.add(assignPermissionDao.save(assignPermission));
		return data;
	}
	
	@Override
	public List<AssignPermission> getAssignPermissionById(String groupId) {
		List<AssignPermission> data = new ArrayList<AssignPermission>();
		data.add(assignPermissionDao.findByGroupid(groupId));
		return data;
	}

	
}
