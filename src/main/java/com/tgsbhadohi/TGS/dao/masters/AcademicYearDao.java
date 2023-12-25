package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.masters.AcademicYear;

public interface AcademicYearDao extends JpaRepository<AcademicYear, Long>{
	public List<AcademicYear> findByActive(boolean status);
}
