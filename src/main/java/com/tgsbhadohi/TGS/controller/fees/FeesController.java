package com.tgsbhadohi.TGS.controller.fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.Fees.FeesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fees")
public class FeesController {
	@Autowired
	private FeesService feesService;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllFees(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,feesService.getAllFees());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/find-by-registration-id")
	private ResponseEntity<ResponseModel> getAcademicYearById(@PathVariable Fees fees){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,feesService.findByRegistrationNo(fees));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> saveFees(@Valid @RequestBody Fees fees) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,feesService.saveFees(fees));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PostMapping("/filter")
	private ResponseEntity<ResponseModel> filterFees(@RequestBody Fees fees){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,feesService.search(fees));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
