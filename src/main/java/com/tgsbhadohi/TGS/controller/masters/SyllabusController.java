package com.tgsbhadohi.TGS.controller.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Syllabus;
import com.tgsbhadohi.TGS.service.masters.SyllabusService;


@RestController
@RequestMapping("/master/syllabus")
@CrossOrigin("*")
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseModel> save(
            @RequestBody Syllabus syllabus) {
    	return new ResponseEntity<>(syllabusService.save(syllabus), HttpStatus.CREATED);
    }

    
    @GetMapping("/findall")
    public ResponseEntity<ResponseModel> findAll() {

    	ResponseModel res = new ResponseModel(
  		      Constants.GET_RECORD,
  		      Constants.SUCCESS,
  		      false,
  		    syllabusService.findAll()
  		    );
  	return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<?> findById(
            @RequestBody Syllabus syllabus) {

        return new ResponseEntity<>(
                syllabusService.findById(
                        syllabus.getSyllabusId()),
                HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseModel> list(@RequestBody Syllabus syllabus) {

    	ResponseModel res = new ResponseModel(
    		      Constants.GET_RECORD,
    		      Constants.SUCCESS,
    		      false,
    		      syllabusService.findByStandardAndAcademicYearCodeAndActiveTrue(
                          syllabus.getStandard(),
                          syllabus.getAcademicYearCode())
    		    );
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {

        syllabusService.delete(id);

        return ResponseEntity.ok("Deleted Successfully");
    }
}