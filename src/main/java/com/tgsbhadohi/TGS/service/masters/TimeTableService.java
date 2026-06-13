package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.TimeTable;

public interface TimeTableService {
	 public ResponseModel save(TimeTable timeTable);

	    List<TimeTable> saveAll(List<TimeTable> timeTableList);

	    List<TimeTable> findAll();

	    TimeTable findById(Long timeTableId);

	    List<TimeTable> findByStandardAndAcademicYearCodeAndActiveTrue(
	            String standard,
	            String academicYearCode);

	    void delete(Long timeTableId);
}
