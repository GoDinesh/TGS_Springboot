package com.tgsbhadohi.TGS.service.Fees;

import java.util.List;

import com.tgsbhadohi.TGS.entities.fees.Fees;


public interface FeesService {
	public List<Fees> getAllFees();
	public List<Fees> saveFees(Fees fees);
	public List<Fees> findByRegistrationNo(Fees fees);
	public List<Fees> search(Fees fees);
}
