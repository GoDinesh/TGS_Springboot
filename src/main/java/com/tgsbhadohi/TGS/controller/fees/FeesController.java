package com.tgsbhadohi.TGS.controller.fees;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.Fees.FeesService;
import com.tgsbhadohi.TGS.service.student.RegistrationService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/fees")
@CrossOrigin("*")
public class FeesController {

  @Autowired
  private FeesService feesService;
  
  @Autowired
  private RegistrationService registrationService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllFees() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.getAllFees()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @GetMapping("/find-by-registration-id")
  private ResponseEntity<ResponseModel> getAcademicYearById(
    @PathVariable Fees fees
  ) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.findByRegistrationNo(fees)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveFees(@Valid @RequestBody Fees fees) {
	  ResponseModel res;
	  try {
		  	res = new ResponseModel(Constants.FEES_PAID, Constants.SUCCESS, true,feesService.saveFees(fees));
	  }catch(Exception ex) {
		    res = new ResponseModel(Constants.FAILURE, Constants.ERROR, true,null);
	  }
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

  @PostMapping("/filter")
  private ResponseEntity<ResponseModel> filterFees(@RequestBody Fees fees) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.search(fees)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @PostMapping("/today-fees-collection")
  private ResponseEntity<ResponseModel> todayFeesCollection(@RequestBody Fees fees) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.todayFeesCollection(fees)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @PostMapping("/filter-by-receipt")
  private ResponseEntity<ResponseModel> filterFeesByReceiptNumber(@RequestBody Fees fees) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.filterFeesByReceiptNumber(fees.getReceiptNo())
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @GetMapping("/get-receipt-number")
  private ResponseEntity<ResponseModel> getReceiptNumber() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.getReceiptNo()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @PostMapping("/pending-fees")
  private ResponseEntity<ResponseModel> getPendingFees(@RequestBody Registration reg) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.getPendingFees(reg)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  
  @PostMapping("/pending-fees-class-wise")
  private ResponseEntity<ResponseModel> getTotalPendingFeesClassWise(@RequestBody Registration reg) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesService.getTotalPendingFeesClassWise(reg)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  

  
  
}
