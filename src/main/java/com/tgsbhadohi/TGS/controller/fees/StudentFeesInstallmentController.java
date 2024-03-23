package com.tgsbhadohi.TGS.controller.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;
import com.tgsbhadohi.TGS.service.Fees.StudentFeesInstallmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fees/student-fees-installment")
@CrossOrigin("*")
public class StudentFeesInstallmentController {
	@Autowired
	private StudentFeesInstallmentService studentFeesInstallmentService ;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllStudentFeesInstallment(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,studentFeesInstallmentService.getAllStudentFeesInstallment());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> saveStudentFeesInstallment(@Valid @RequestBody StudentFeesInstallment studentFeesInstallment) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,studentFeesInstallmentService.saveStudentFeesInstallment(studentFeesInstallment));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
}
