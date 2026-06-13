package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.SyllabusDao;
import com.tgsbhadohi.TGS.dao.masters.TimeTableDao;
import com.tgsbhadohi.TGS.entities.masters.Syllabus;
import com.tgsbhadohi.TGS.entities.masters.TimeTable;

@Service
public class TimeTableImpl implements TimeTableService{
	@Autowired
    private TimeTableDao timeTableDao;

    @Override
    public ResponseModel save(TimeTable timeTable) {
        List<TimeTable> data = new ArrayList<TimeTable>();
        
        ResponseModel res = new ResponseModel();
        if(timeTable.getTimeTableId()>0) {
        	data.add(timeTableDao.save(timeTable));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
        	if (timeTableDao.countByStandardAndAcademicYearCode(
        	        timeTable.getStandard(),
        	        timeTable.getAcademicYearCode()) > 0) {

        	    res = new ResponseModel(
        	            Constants.DUPLICATE_RECORD,
        	            Constants.ERROR,
        	            true,
        	            null);
        	}else {
				data.add(timeTableDao.save(timeTable));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
    }

    @Override
    public List<TimeTable> saveAll(List<TimeTable> timeTableList) {
        return timeTableDao.saveAll(timeTableList);
    }

    @Override
    public List<TimeTable> findAll() {
        return timeTableDao.findAll();
    }

    @Override
    public TimeTable findById(Long timeTableId) {
        return timeTableDao.findById(timeTableId)
                .orElseThrow(() -> new RuntimeException("Syllabus Not Found"));
    }

    @Override
    public List<TimeTable> findByStandardAndAcademicYearCodeAndActiveTrue(
            String standard,
            String academicYearCode) {

        return timeTableDao.findByStandardAndAcademicYearCodeAndActiveTrue(
                standard,
                academicYearCode);
    }

    @Override
    public void delete(Long timeTableId) {
    	timeTableDao.deleteById(timeTableId);
    }
}

