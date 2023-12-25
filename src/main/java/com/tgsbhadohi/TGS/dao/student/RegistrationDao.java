package com.tgsbhadohi.TGS.dao.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tgsbhadohi.TGS.entities.student.Registration;



@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long>{
	
	//@Query(value = "SELECT COALESCE(max(roll_number+1) , 1 ) as roll_number from registration where academic_year_code = :academicYear and standard = :standard", nativeQuery = true)
	@Query(value = "SELECT COALESCE(max(registration_id+1),1) as roll_number from registration", nativeQuery = true)
	int getRollNumber(@Param("academicYear") String academicYear, @Param("standard") String standard);
	
	@Query("SELECT r FROM Registration r WHERE "
				+ "r.studentName LIKE %?1%"
	            + " OR r.aadhaarNumber LIKE %?1%"
	            + " OR r.registrationNo LIKE %?1%")
	public List<Registration> filterListByKeyword(String keyword);
	
//	@Modifying	
//	@Query("update Registration set category=false where registrationId= ?1")
//	int updateIsActiveByRegistartionId(Long registartionid);
	
	@Modifying
	@Query("update Registration r set r.isActive = false where r.registrationId = :registrationId")
	@Transactional
    int updateIsActiveByRegistartionId(@Param("registrationId") Long registrationId);
}
