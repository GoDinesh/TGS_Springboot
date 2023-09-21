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
}
