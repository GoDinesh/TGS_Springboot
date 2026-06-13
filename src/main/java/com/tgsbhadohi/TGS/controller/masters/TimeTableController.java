package com.tgsbhadohi.TGS.controller.masters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.TimeTable;
import com.tgsbhadohi.TGS.service.masters.TimeTableService;


@RestController
@RequestMapping("/master/timetable")
@CrossOrigin("*")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseModel> save(
            @RequestBody TimeTable timeTable) {
    	return new ResponseEntity<>(timeTableService.save(timeTable), HttpStatus.CREATED);
    }

    
    @GetMapping("/findall")
    public ResponseEntity<ResponseModel> findAll() {

    	ResponseModel res = new ResponseModel(
  		      Constants.GET_RECORD,
  		      Constants.SUCCESS,
  		      false,
  		      timeTableService.findAll()
  		    );
  	return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/find-by-id")
    public ResponseEntity<?> findById(
            @RequestBody TimeTable timeTable) {

        return new ResponseEntity<>(
        		timeTableService.findById(
        				timeTable.getTimeTableId()),
                HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseModel> list(@RequestBody TimeTable timeTable) {

    	ResponseModel res = new ResponseModel(
    		      Constants.GET_RECORD,
    		      Constants.SUCCESS,
    		      false,
    		      timeTableService.findByStandardAndAcademicYearCodeAndActiveTrue(
    		    		  timeTable.getStandard(),
    		    		  timeTable.getAcademicYearCode())
    		    );
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {

    	timeTableService.delete(id);

        return ResponseEntity.ok("Deleted Successfully");
    }
}