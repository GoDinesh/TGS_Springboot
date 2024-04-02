package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

public interface DiscountReasonService {
	public List<DiscountReason> getAllDiscountReason();
	public ResponseModel saveDiscountReason(DiscountReason discountReason);
	public List<DiscountReason> getAllActiveDiscountReason(Boolean status);
}
