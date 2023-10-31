package com.tgsbhadohi.TGS.controller.masters;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.service.masters.FeesStructureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/fees-structure")
public class FeesStructureController {

  @Autowired
  private FeesStructureService feesStructureService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllFeesStructure() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesStructureService.getAllFeesStructure()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveFeesType(
    @Valid @RequestBody FeesStructure feesStructure
  ) {
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      true,
      feesStructureService.saveFeesStructure(feesStructure)
    );
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

  @PostMapping("/findbyid")
  private ResponseEntity<ResponseModel> getFeeStructureById(
    @Valid @RequestBody FeesStructure feesStructure
  ) {
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      false,
      feesStructureService.getFeeStructureById(feesStructure)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
