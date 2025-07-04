package com.tgsbhadohi.TGS.controller.waap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.service.waap.WaappathwayService;

@RestController
@RequestMapping("/waap/waappathway")
@CrossOrigin("*")
public class WaappathwayController {
	
	@Autowired
	private WaappathwayService waappathwayService;
	
	@GetMapping("/findall")
	  private ResponseEntity<ResponseModel> getAllWaappathway() {
	    ResponseModel res = new ResponseModel(
	      Constants.GET_RECORD,
	      Constants.SUCCESS,
	      false,
	      waappathwayService.getWaappathwayDetails()
	    );
	    return new ResponseEntity<>(res, HttpStatus.OK);
	  }
}
