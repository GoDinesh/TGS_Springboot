package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.masters.Holiday;


public interface HolidayDao extends JpaRepository<Holiday, Long>{
	public List<Holiday> findByActive(boolean status);
	public Integer countByName(String academicYear);
	public List<Holiday> findByAcademicYearCodeAndActive(String academicYearCode, boolean active);
	
}
