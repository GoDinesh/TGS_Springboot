package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.entities.masters.AcademicYear;


public interface AcademicYearService {
	public List<AcademicYear> getAllAcademicYear();
	public List<AcademicYear> getAcademicYearById(Long id);
	public List<AcademicYear> saveAcademicYear(AcademicYear standard);
}
