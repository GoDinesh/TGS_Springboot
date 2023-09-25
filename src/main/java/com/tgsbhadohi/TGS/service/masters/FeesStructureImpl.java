package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.FeesStructureDao;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;

@Service
public class FeesStructureImpl implements FeesStructureService{

	@Autowired
	private FeesStructureDao feesStructureDao;
	
	@Override
	public List<FeesStructure> getAllFeesStructure() {
		return feesStructureDao.findAll();
	}

	@Override
	public List<FeesStructure> saveFeesStructure(FeesStructure feesStructure) {
		List<FeesStructure> data = new ArrayList<FeesStructure>();
		data.add(feesStructureDao.save(feesStructure));
		return data;
	}
}
