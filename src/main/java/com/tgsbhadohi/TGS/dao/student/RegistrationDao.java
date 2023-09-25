package com.tgsbhadohi.TGS.dao.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.student.Registration;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long>{
	
	@Query(value = "SELECT COALESCE(max(roll_number+1) , 1 ) as roll_number from registration where academic_year_code = :academicYear and standard = :standard", nativeQuery = true)
	int getRollNumber(@Param("academicYear") String academicYear, @Param("standard") String standard);
	
	@Query("SELECT r FROM Registration r WHERE "
				+ "r.studentName LIKE %?1%"
	            + " OR r.aadhaarNumber LIKE %?1%"
	            + " OR r.registrationNo LIKE %?1%")
	public List<Registration> filterListByKeyword(String keyword);
	
//	@Query("SELECT p FROM Registration p WHERE CONCAT(p.studentName, p.aadhaarNumber, p.registrationNo) LIKE '%?1%'")
	//@Query("SELECT p FROM Registration p") //CONCAT(p.studentName, p.aadhaarNumber, p.registrationNo) LIKE '%?1%'")
//	@Query(value = "SELECT * from registration p where  CONCAT(p.student_name, p.aadhaar_number, p.registration_no) LIKE %:keyword%", nativeQuery = true)
//	List<Registration> filterListByKeyword(@Param("keyword") String keyword);
	//public List<Registration> filterListByKeyword(String keyword);
}
