package com.tgsbhadohi.TGS.controller.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.service.Fees.StudentFeesStructureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student/student-fees-structure")
@CrossOrigin("*")
public class StudentFeesStructureController {
	@Autowired
	private StudentFeesStructureService studentFeesStructureService;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllStudentFeesStructure(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,studentFeesStructureService.getAllStudentFeesStructure());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> saveFees(@Valid @RequestBody StudentFeesStructure studentFeesStructure) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, false ,studentFeesStructureService.saveStudentFeesStructure(studentFeesStructure));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
//	@PostMapping("/findbyid")
//	private ResponseEntity<ResponseModel> getStudentFeeStructureById(@Valid @RequestBody StudentFeesStructure studentFeesStructure) {
//		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false,studentFeesStructureService.search(studentFeesStructure));
//		return new ResponseEntity<>(res, HttpStatus.OK);
//	}
}
