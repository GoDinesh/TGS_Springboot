package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Holiday;

public interface HolidayService {
	public List<Holiday> getAllHoliday();
	public List<Holiday> getHolidayByAcademicYearCode(String academicYearCode);
	public ResponseModel saveHoliday(Holiday holiday);
	public List<Holiday> getAllHoliday(Boolean status);
}
