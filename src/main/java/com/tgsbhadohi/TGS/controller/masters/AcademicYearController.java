package com.tgsbhadohi.TGS.controller.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.Standard;
import com.tgsbhadohi.TGS.service.masters.AcademicYearService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/master/academicyear")
public class AcademicYearController {
	
	@Autowired
	private AcademicYearService academicYearService;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllAcademicYear(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,academicYearService.getAllAcademicYear());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ResponseModel> getAcademicYearById(@PathVariable String id){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,academicYearService.getAcademicYearById(Long.parseLong(id)));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> saveAcademicYear(@Valid @RequestBody AcademicYear academicYear) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,academicYearService.saveAcademicYear(academicYear));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	

}
