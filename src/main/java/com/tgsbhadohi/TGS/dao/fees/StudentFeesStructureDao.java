package com.tgsbhadohi.TGS.dao.fees;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;

public interface StudentFeesStructureDao extends JpaRepository<StudentFeesStructure, Long> {
	//List<StudentFeesStructure> findByRegistrationNoAndAcademicYearCodeAndClassCode(String userRegistrationNo, String academicYearCode, String classCode);
//	@Query(value = "SELECT * from  student_fees_structure", nativeQuery = true)
//	List<StudentFeesStructure> findByRegistrationNoAndAcademicYearCodeAndClassCode(@Param("userRegistrationNo") String userRegistrationNo, @Param("academicYearCode") String academicYearCode, @Param("classCode") String classCode);
}
