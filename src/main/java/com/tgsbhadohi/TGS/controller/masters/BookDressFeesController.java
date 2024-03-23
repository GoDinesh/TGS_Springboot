package com.tgsbhadohi.TGS.controller.masters;

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
import com.tgsbhadohi.TGS.entities.masters.BookDressFees;
import com.tgsbhadohi.TGS.service.masters.BookDressFeesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/master/book-dress-fees")
@CrossOrigin("*")
public class BookDressFeesController {
	  @Autowired
	  private BookDressFeesService bookDressFeesService;

	  @GetMapping("/findall")
	  private ResponseEntity<ResponseModel> getAllBookDressFees() {
	    ResponseModel res = new ResponseModel(
	      Constants.GET_RECORD,
	      Constants.SUCCESS,
	      false,
	      bookDressFeesService.getAllBookDressFees()
	    );
	    return new ResponseEntity<>(res, HttpStatus.OK);
	  }
	  
	  @PostMapping("/insert")
	  private ResponseEntity<ResponseModel> saveBookDressFees(@Valid @RequestBody BookDressFees bookDressFees) {
		  try {
			  	ResponseModel res = new ResponseModel( Constants.CREATE_RECORD, Constants.SUCCESS, true, bookDressFeesService.saveBookDressFees(bookDressFees));
			  	return new ResponseEntity<>(res, HttpStatus.CREATED);
		  }catch(Exception ex) {
			  ResponseModel res = new ResponseModel( Constants.DUPLICATE_RECORD, Constants.ERROR, true, null);
			  return new ResponseEntity<>(res, HttpStatus.CREATED);
		  }
	  }
	  
	  @PostMapping("/findbyid")
	  private ResponseEntity<ResponseModel> findByAcademicYearCodeAndStandard(@Valid @RequestBody BookDressFees bookDressFees) {
		    	ResponseModel res = new ResponseModel( Constants.GET_RECORD, Constants.SUCCESS, false, bookDressFeesService.findByAcademicYearCodeAndStandard(bookDressFees));
			  	return new ResponseEntity<>(res, HttpStatus.CREATED);
		  
	  }
	  
	  
}
