package com.tgsbhadohi.TGS.controller.masters;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Holiday;
import com.tgsbhadohi.TGS.service.masters.HolidayService;

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
@RequestMapping("/master/holiday")
@CrossOrigin("*")
public class HolidayController {
  @Autowired
  private HolidayService holidayService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllHoliday() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      holidayService.getAllHoliday()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @GetMapping("/allActiveHoliday")
  private ResponseEntity<ResponseModel> getAllActiveHoliday() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      holidayService.getAllHoliday(true)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  

  @PostMapping("/getByAcademicYearCode")
  private ResponseEntity<ResponseModel> getHolidayById(
  @RequestBody Holiday holiday
  ) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      holidayService.getHolidayByAcademicYearCode(holiday.getAcademicYearCode())
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveHoliday(
    @Valid @RequestBody Holiday holiday
  ) {
	  return new ResponseEntity<>(holidayService.saveHoliday(holiday), HttpStatus.CREATED);
  }
}
