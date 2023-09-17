package com.tgsbhadohi.TGS.controller.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.PermissionGroup;
import com.tgsbhadohi.TGS.service.masters.PermissionGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/master/permission-group")
public class PermissionGroupController {
	
	@Autowired
	private PermissionGroupService permissionGroupService;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllPermissionGroup(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,permissionGroupService.getAllPermissionGroup());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> savePermissionGroup(@Valid @RequestBody PermissionGroup permissionGroup) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,permissionGroupService.savePermissionGroup(permissionGroup));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

}
