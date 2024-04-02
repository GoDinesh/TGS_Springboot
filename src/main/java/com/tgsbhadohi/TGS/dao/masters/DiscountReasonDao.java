package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.AcademicYear;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

@Repository
public interface DiscountReasonDao extends JpaRepository<DiscountReason, Long>{
	public List<DiscountReason> findByActive(boolean status);
	public Integer countByDiscountReason(String discountReason);
}
