package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.DiscountReasonDao;
import com.tgsbhadohi.TGS.entities.masters.DiscountReason;

@Service
public class DiscountReasonImpl implements DiscountReasonService{

	@Autowired
	private DiscountReasonDao discountReasonDao;
	
	@Override
	public List<DiscountReason> getAllDiscountReason() {
		return this.discountReasonDao.findAll(Sort.by("discountReason"));
	}

	@Override
	public ResponseModel saveDiscountReason(DiscountReason discountReason) {
		List<DiscountReason> data = new ArrayList<DiscountReason>();
		ResponseModel res = new ResponseModel();
//		data.add(discountReasonDao.save(discountReason));
//		return data;
		if(discountReason.getDiscountReasonCode()>0) {
        	data.add(discountReasonDao.save(discountReason));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(discountReasonDao.countByDiscountReason(discountReason.getDiscountReason().trim())>0) {
				res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
			}else {
				data.add(discountReasonDao.save(discountReason));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
	}

	@Override
	public List<DiscountReason> getAllActiveDiscountReason(Boolean status) {
		return discountReasonDao.findByActive(true);
	}
	
}
