package com.tgsbhadohi.TGS.controller.masters;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Subject;
import com.tgsbhadohi.TGS.service.masters.SubjectService;

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
@RequestMapping("/master/subject")
@CrossOrigin("*")
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  @GetMapping("/findall")
  private ResponseEntity<ResponseModel> getAllSubject() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      subjectService.getAllSubject()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  
  @GetMapping("/allActiveRecords")
  private ResponseEntity<ResponseModel> getAllActiveSubject() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      subjectService.getAllActiveSubject(true)
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
  

  @GetMapping("/{id}")
  private ResponseEntity<ResponseModel> getSubjectById(
    @PathVariable String id
  ) {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      subjectService.getSubjectById(Long.parseLong(id))
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @PostMapping("/insert")
  private ResponseEntity<ResponseModel> saveSubject(
    @Valid @RequestBody Subject subject
  ) {
	  return new ResponseEntity<>(subjectService.saveSubject(subject), HttpStatus.CREATED);
  }
}
