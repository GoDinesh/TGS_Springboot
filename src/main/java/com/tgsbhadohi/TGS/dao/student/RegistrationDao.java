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
	
	public Integer countByAadhaarNumber(String aadhaarNumber);
	public Integer countByFatherContactNoOrMotherContactNumberOrEmergencyNumber(String FatherContactNo, String MotherContactNumber, String EmergencyNumber);
	
	@Query(value = "SELECT COALESCE(max(roll_number+1) , 1 ) as roll_number from registration where academic_year_code = :academicYear and standard = :standard", nativeQuery = true)
	int getRollNumber(@Param("academicYear") String academicYear, @Param("standard") String standard);
	
	@Query(value = "SELECT count(distinct(registration_no))+1 as roll_number FROM tgs.registration", nativeQuery = true)
	int getRegistrationNumber();
	
	@Query("SELECT r FROM Registration r WHERE "
				+ "r.studentName LIKE %?1%"
	            + " OR r.aadhaarNumber LIKE %?1%"
	            + " OR r.registrationNo LIKE %?1%")
	public List<Registration> filterListByKeyword(String keyword);
	
//	@Modifying	
//	@Query("update Registration set category=false where registrationId= ?1")
//	int updateIsActiveByRegistartionId(Long registartionid);serve
	
	
	@Modifying
	@Query("update Registration r set r.isActive = false where r.registrationId = :registrationId")
	@Transactional
    int updateIsActiveByRegistartionId(@Param("registrationId") Long registrationId);
	
	@Modifying
	//@Query("update Registration r set r.paidFees = :paidFees, r.pendingFees= :pendingFees, r.isTotalFeesPaid= :isTotalFeesPaid, r.totalFees= :totalFees where r.registrationId = :registrationId")
	@Query("update Registration r set r.paidFees = :paidFees, r.pendingFees= :pendingFees, r.isTotalFeesPaid= :isTotalFeesPaid where r.registrationId = :registrationId")
	@Transactional
    int updateFeesDetailsByRegistrationId(@Param("registrationId") Long registrationId,
    		@Param("paidFees") double paidFees,
    		@Param("pendingFees") double pendingFees,
    		@Param("isTotalFeesPaid") boolean isTotalFeesPaid);
	
}
