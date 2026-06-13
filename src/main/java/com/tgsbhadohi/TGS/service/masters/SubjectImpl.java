package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.SubjectDao;
import com.tgsbhadohi.TGS.entities.masters.Subject;

@Service
public class SubjectImpl implements SubjectService{
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public List<Subject> getAllSubject() {
		return subjectDao.findAll(Sort.by("subject"));
	}
	
	
	@Override
	public List<Subject> getSubjectById(Long id) {
		List<Subject> data = new ArrayList<Subject>();
		data.add(subjectDao.findById(id).get());
		return data;
		 
	}

	@Override
	public ResponseModel saveSubject(Subject subject) {
		List<Subject> data = new ArrayList<Subject>();
        ResponseModel res = new ResponseModel();
        if(subject.getId()>0) {
        	data.add(subjectDao.save(subject));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(subjectDao.countBySubject(subject.getSubject())>0) {
				res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
			}else {
				data.add(subjectDao.save(subject));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
	}

	@Override
	public List<Subject> getAllActiveSubject(Boolean status) {
		return subjectDao.findByActive(true);
	}

}
