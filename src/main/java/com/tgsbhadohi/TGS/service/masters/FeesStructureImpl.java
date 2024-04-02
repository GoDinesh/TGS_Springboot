package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.FeesStructureDao;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.entities.masters.Installment;

@Service
public class FeesStructureImpl implements FeesStructureService{

	@Autowired
	private FeesStructureDao feesStructureDao;
	
	@Override
	public List<FeesStructure> getAllFeesStructure() {
		return feesStructureDao.findAll(Sort.by("academicYearCode"));
	}

	@Override
	public ResponseModel saveFeesStructure(FeesStructure feesStructure) {
		List<FeesStructure> data = new ArrayList<FeesStructure>();
		ResponseModel res = new ResponseModel();
//		data.add(feesStructureDao.save(feesStructure));
//		return data;
		 
	        if(feesStructure.getFeeStructureId()>0) {
	        	data.add(feesStructureDao.save(feesStructure));
				res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
	        }else {
				if(feesStructureDao.countByAcademicYearCodeAndClassCodeAndEnrollmentType(feesStructure.getAcademicYearCode(), feesStructure.getClassCode(), feesStructure.getEnrollmentType())>0) {
					res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
				}else {
					data.add(feesStructureDao.save(feesStructure));
					res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
				}
	        }
			return res;
	}

	@Override
	public List<FeesStructure> getFeeStructureById(FeesStructure feesStructure) {
		return feesStructureDao.findByAcademicYearCodeAndClassCodeAndEnrollmentType(feesStructure.getAcademicYearCode(),
				feesStructure.getClassCode(), feesStructure.getEnrollmentType());
	}

	@Override
	public List<FeesStructure> getAllActiveFeesStructure(Boolean status) {
		return feesStructureDao.findByActive(status);
	}
}
