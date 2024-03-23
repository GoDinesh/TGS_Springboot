package com.tgsbhadohi.TGS.controller.masters;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Standard;
import com.tgsbhadohi.TGS.service.masters.StandardService;
import jakarta.validation.Valid;
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
@RequestMapping("/master/standard")
@CrossOrigin("*")
public class StandardController {

  @Autowired
  private StandardService standardService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllStandard() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      standardService.getAllStandard()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @GetMapping("/allActiveRecords")
  private ResponseEntity<ResponseModel> getAllActiveStandard() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      standardService.getAllActiveStandard(true)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  

  @GetMapping("/{id}")
  private ResponseEntity<ResponseModel> getStandardById(
    @PathVariable String id
  ) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      standardService.getStandardById(Long.parseLong(id))
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveStandard(
    @Valid @RequestBody Standard standard
  ) {
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      true,
      standardService.saveStandard(standard)
    );
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }
}
