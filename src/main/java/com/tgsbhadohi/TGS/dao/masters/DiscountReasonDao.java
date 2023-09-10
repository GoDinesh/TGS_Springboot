package com.tgsbhadohi.TGS.dao.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

@Repository
public interface DiscountReasonDao extends JpaRepository<DiscountReason, Long>{

}
