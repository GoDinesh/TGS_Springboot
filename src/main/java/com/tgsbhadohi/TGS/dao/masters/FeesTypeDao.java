package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.FeesType;

@Repository
public interface FeesTypeDao extends JpaRepository<FeesType, Long> {
	public List<FeesType> findByActive(boolean status);
}
