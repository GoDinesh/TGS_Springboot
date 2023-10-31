package com.tgsbhadohi.TGS.controller.masters;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;
import com.tgsbhadohi.TGS.entities.masters.FeesType;
import com.tgsbhadohi.TGS.service.masters.FeesTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master/feestype")
public class FeesTypeController {

  @Autowired
  private FeesTypeService feesTypeService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllFeesType() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      feesTypeService.getAllFeesType()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveFeesType(
    @Valid @RequestBody FeesType feesType
  ) {
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      true,
      feesTypeService.saveFeesType(feesType)
    );
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }
}
