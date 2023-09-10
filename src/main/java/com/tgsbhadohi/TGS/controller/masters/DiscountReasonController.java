package com.tgsbhadohi.TGS.controller.masters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;
import com.tgsbhadohi.TGS.service.masters.DiscountReasonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/master/discountreason")
public class DiscountReasonController {

	@Autowired
	private DiscountReasonService discountReasonService;
	
	@GetMapping("/findall")
	private ResponseEntity<ResponseModel> getAllDiscountReason(){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,discountReasonService.getAllDiscountReason());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	private ResponseEntity<ResponseModel> saveDiscountReason(@Valid @RequestBody DiscountReason discountReason) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,discountReasonService.saveDiscountReason(discountReason));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
}
