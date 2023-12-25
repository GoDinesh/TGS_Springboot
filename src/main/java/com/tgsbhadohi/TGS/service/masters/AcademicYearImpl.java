package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	public List<AcademicYear> saveAcademicYear(AcademicYear academicYear) {
		List<AcademicYear> data = new ArrayList<AcademicYear>();
		data.add(academicYearDao.save(academicYear));
		return data;
		
	}

	@Override
	public List<AcademicYear> getAllActiveAcademicYear(Boolean status) {
		return academicYearDao.findByActive(true);
	}

}
