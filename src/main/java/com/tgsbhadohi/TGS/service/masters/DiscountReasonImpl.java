package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.DiscountReasonDao;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

@Service
public class DiscountReasonImpl implements DiscountReasonService{

	@Autowired
	private DiscountReasonDao discountReasonDao;
	
	@Override
	public List<DiscountReason> getAllDiscountReason() {
		return this.discountReasonDao.findAll();
	}

	@Override
	public List<DiscountReason> saveDiscountReason(DiscountReason discountReason) {
		List<DiscountReason> data = new ArrayList<DiscountReason>();
		data.add(discountReasonDao.save(discountReason));
		return data;		
	}
	
}
