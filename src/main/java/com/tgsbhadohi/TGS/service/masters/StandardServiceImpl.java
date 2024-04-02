package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.StandardDao;
import com.tgsbhadohi.TGS.entities.masters.Standard;

@Service
public class StandardServiceImpl implements StandardService {
	
	@Autowired
	private StandardDao standardDao;
	
	@Override
	public List<Standard> getAllStandard() {
		return standardDao.findAll(Sort.by("className"));
	}

	@Override
	public List<Standard> getStandardById(Long id) {
		List<Standard> data = new ArrayList<Standard>();
		data.add(standardDao.findById(id).get());
		return data;
	}

	@Override
	public ResponseModel saveStandard(Standard standard) {
		List<Standard> data = new ArrayList<Standard>();
//		data.add(standardDao.save(standard));
//		return data;
		ResponseModel res = new ResponseModel();
        if(standard.getId()>0) {
        	data.add(standardDao.save(standard));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(standardDao.countByClassCode( standard.getClassCode())>0) {
				res = new ResponseModel(Constants.DUPLICATE_CLASS_CODE, Constants.ERROR, true, null); 
			}else if(standardDao.countByClassName( standard.getClassName())>0) {
				res = new ResponseModel(Constants.DUPLICATE_CLASS_NAME, Constants.ERROR, true, null);
			}else {
				data.add(standardDao.save(standard));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
	}

	@Override
	public List<Standard> getAllActiveStandard(Boolean status) {
		return standardDao.findByActive(status);
	}
}
