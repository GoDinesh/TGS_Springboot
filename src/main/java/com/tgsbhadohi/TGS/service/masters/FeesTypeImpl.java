package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.FeesTypeDao;
import com.tgsbhadohi.TGS.entities.masters.FeesType;

@Service
public class FeesTypeImpl implements FeesTypeService {

	@Autowired
	private FeesTypeDao feesTypeDao;
	
	@Override
	public List<FeesType> getAllFeesType() {
		return feesTypeDao.findAll();
	}

	@Override
	public List<FeesType> saveFeesType(FeesType feesType) {
		List<FeesType> data = new ArrayList<FeesType>();
		data.add(feesTypeDao.save(feesType));
		return data;
	}

}
