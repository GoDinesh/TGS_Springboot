package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.FeesStructureDao;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;

@Service
public class FeesStructureImpl implements FeesStructureService{

	@Autowired
	private FeesStructureDao feesStructureDao;
	
	@Override
	public List<FeesStructure> getAllFeesStructure() {
		return feesStructureDao.findAll(Sort.by("academicYearCode"));
	}

	@Override
	public List<FeesStructure> saveFeesStructure(FeesStructure feesStructure) {
		List<FeesStructure> data = new ArrayList<FeesStructure>();
		data.add(feesStructureDao.save(feesStructure));
		return data;
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
