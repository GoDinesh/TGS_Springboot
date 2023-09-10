package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

public interface DiscountReasonService {
	public List<DiscountReason> getAllDiscountReason();
	public List<DiscountReason> saveDiscountReason(DiscountReason discountReason);

}
