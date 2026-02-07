package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.HolidayDao;
import com.tgsbhadohi.TGS.entities.masters.Holiday;

@Service
public class HolidayImpl implements HolidayService{
	@Autowired
	private HolidayDao holidayDao;

	@Override
	public List<Holiday> getAllHoliday() {
		return holidayDao.findAll(Sort.by("academicYearCode"));
	}
	
	
	@Override
	public List<Holiday> getHolidayByAcademicYearCode(String academicYearCode) {
		List<Holiday> data = new ArrayList<Holiday>();
		data.addAll(holidayDao.findByAcademicYearCodeAndActive(academicYearCode, true));
		return data;		 
	}

	@Override
	public ResponseModel saveHoliday(Holiday holiday) {
		List<Holiday> data = new ArrayList<Holiday>();
        ResponseModel res = new ResponseModel();
        if(holiday.getId()>0) {
        	data.add(holidayDao.save(holiday));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(holidayDao.countByName(holiday.getName())>0) {
				res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
			}else {
				data.add(holidayDao.save(holiday));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
	}

	@Override
	public List<Holiday> getAllHoliday(Boolean status) {
		return holidayDao.findByActive(true);
	}
	
	
}


