package com.tgsbhadohi.TGS.dao.fees;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.student.Registration;

public interface FeesDao extends JpaRepository<Fees, Long> {
		List<Fees> findByRegistrationNo(String registrationNo);
		List<Fees> findTopByOrderByIdDesc();
		List<Fees> findByReceiptNo(String receiptNo);
		
		
		
		@Query(value="SELECT *  FROM fees f where f.academic_year_code = :academicYear  order by academic_year_code desc, receipt_id_academic_year_wise desc limit 1", nativeQuery = true)
		List<Fees> getFeesReceiptNumberAcademicYearWise(@Param("academicYear") String academicYear);
		
		
		
	

}
