package com.tgsbhadohi.TGS.service.waap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.waap.WaappathwayDao;
import com.tgsbhadohi.TGS.entities.waap.Waappathway;

@Service
public class WaappathwayImpl implements WaappathwayService{

	@Autowired
	private WaappathwayDao waappathwayDao;
	
	@Override
	public List<Waappathway> getWaappathwayDetails() {
		return waappathwayDao.findAll();
	}
}
