package com.tgsbhadohi.TGS.service.Fees;

import java.util.List;

import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.student.Registration;


public interface FeesService {
	public List<Fees> getAllFees();
	public List<Fees> saveFees(Fees fees);
	public List<Fees> findByRegistrationNo(Fees fees);
	public List<Fees> search(Fees fees);
	public String getReceiptNo();
	public List<Fees> filterFeesByReceiptNumber(String receiptNo);
	public List<Registration> getPendingFees(Registration registration);
	public List<String> getTotalPendingFeesClassWise(Registration registration);
}
