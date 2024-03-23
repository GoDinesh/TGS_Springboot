package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
@Repository
public interface FeesStructureDao extends JpaRepository<FeesStructure, Long> {
	public List<FeesStructure> findByAcademicYearCodeAndClassCodeAndEnrollmentType(String academicYearCode, String classCode, String enrollmentType);
	public List<FeesStructure> findByActive(boolean status);
}
