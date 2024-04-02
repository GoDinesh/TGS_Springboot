package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.AcademicYearDao;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.Standard;
@Service
public class AcademicYearImpl implements AcademicYearService{

	@Autowired
	private AcademicYearDao academicYearDao;
	
	@Override
	public List<AcademicYear> getAllAcademicYear() {
		return academicYearDao.findAll(Sort.by("academicYear"));
	}
	
	
	@Override
	public List<AcademicYear> getAcademicYearById(Long id) {
		List<AcademicYear> data = new ArrayList<AcademicYear>();
		data.add(academicYearDao.findById(id).get());
		return data;
		 
	}

	@Override
	public ResponseModel saveAcademicYear(AcademicYear academicYear) {
		List<AcademicYear> data = new ArrayList<AcademicYear>();
        ResponseModel res = new ResponseModel();
        if(academicYear.getId()>0) {
        	data.add(academicYearDao.save(academicYear));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(academicYearDao.countByAcademicYear(academicYear.getAcademicYear())>0) {
				res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
			}else {
				data.add(academicYearDao.save(academicYear));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
	}

	@Override
	public List<AcademicYear> getAllActiveAcademicYear(Boolean status) {
		return academicYearDao.findByActive(true);
	}

}
