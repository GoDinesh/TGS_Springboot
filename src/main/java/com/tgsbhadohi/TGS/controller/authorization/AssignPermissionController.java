package com.tgsbhadohi.TGS.controller.authorization;

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
import com.tgsbhadohi.TGS.entities.authorization.AssignPermission;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.service.authorization.AssignPermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/assign-permission")
public class AssignPermissionController {

		@Autowired
		private AssignPermissionService assignPermissionService;
	
		@GetMapping("/findall")
		private ResponseEntity<ResponseModel> getAllAssignPermission (){
			ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,assignPermissionService.getAllAssignPermission());
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
		@PostMapping("/insert")
		private ResponseEntity<ResponseModel> saveAssignPermission(@Valid @RequestBody AssignPermission assignPermission) {
			ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,assignPermissionService.saveAssignPermission(assignPermission));
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		}
		
		@PostMapping("/findbyid")
		private ResponseEntity<ResponseModel> getAssignPermissionById(@Valid @RequestBody String roleId) {
			ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, false ,assignPermissionService.getAssignPermissionById(roleId));
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		}
}
