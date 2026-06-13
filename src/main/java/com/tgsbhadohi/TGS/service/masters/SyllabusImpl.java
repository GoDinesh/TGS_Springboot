package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.SyllabusDao;
import com.tgsbhadohi.TGS.entities.masters.Syllabus;


@Service
public class SyllabusImpl implements SyllabusService {

    @Autowired
    private SyllabusDao syllabusDao;

    @Override
    public ResponseModel save(Syllabus syllabus) {
        //return syllabusDao.save(syllabus);
    	List<Syllabus> data = new ArrayList<Syllabus>();
        
        ResponseModel res = new ResponseModel();
        if(syllabus.getSyllabusId()>0) {
        	data.add(syllabusDao.save(syllabus));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
        	if (syllabusDao.countByStandardAndAcademicYearCode(
        	        syllabus.getStandard(),
        	        syllabus.getAcademicYearCode()) > 0) {

        	    res = new ResponseModel(
        	            Constants.DUPLICATE_RECORD,
        	            Constants.ERROR,
        	            true,
        	            null);
        	}else {
				data.add(syllabusDao.save(syllabus));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
    }

    @Override
    public List<Syllabus> saveAll(List<Syllabus> syllabusList) {
        return syllabusDao.saveAll(syllabusList);
    }

    @Override
    public List<Syllabus> findAll() {
        return syllabusDao.findAll();
    }

    @Override
    public Syllabus findById(Long syllabusId) {
        return syllabusDao.findById(syllabusId)
                .orElseThrow(() -> new RuntimeException("Syllabus Not Found"));
    }

    @Override
    public List<Syllabus> findByStandardAndAcademicYearCodeAndActiveTrue(
            String standard,
            String academicYearCode) {

        return syllabusDao.findByStandardAndAcademicYearCodeAndActiveTrue(
                standard,
                academicYearCode);
    }

    @Override
    public void delete(Long syllabusId) {
        syllabusDao.deleteById(syllabusId);
    }
}