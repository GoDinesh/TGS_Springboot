package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.masters.StandardDao;
import com.tgsbhadohi.TGS.entities.masters.Standard;

@Service
public class StandardServiceImpl implements StandardService {
	
	@Autowired
	private StandardDao standardDao;
	
	@Override
	public List<Standard> getAllStandard() {
		return standardDao.findAll();
	}

	@Override
	public List<Standard> getStandardById(Long id) {
		List<Standard> data = new ArrayList<Standard>();
		data.add(standardDao.findById(id).get());
		return data;
	}

	@Override
	public List<Standard> saveStandard(Standard standard) {
		List<Standard> data = new ArrayList<Standard>();
		data.add(standardDao.save(standard));
		return data;
	}
}
