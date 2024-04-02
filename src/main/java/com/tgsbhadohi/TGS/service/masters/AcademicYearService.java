package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.AcademicYear;


public interface AcademicYearService {
	public List<AcademicYear> getAllAcademicYear();
	public List<AcademicYear> getAcademicYearById(Long id);
	public ResponseModel saveAcademicYear(AcademicYear standard);
	public List<AcademicYear> getAllActiveAcademicYear(Boolean status);
}
