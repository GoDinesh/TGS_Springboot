package com.tgsbhadohi.TGS.dao.fees;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.fees.Fees;

public interface FeesDao extends JpaRepository<Fees, Long> {
		List<Fees> findByRegistrationNo(String registrationNo);
		List<Fees> findTopByOrderByIdDesc();
		List<Fees> findByReceiptNo(String receiptNo);

}
