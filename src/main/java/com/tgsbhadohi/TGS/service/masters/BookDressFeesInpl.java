package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.masters.BookDressFeesDao;
import com.tgsbhadohi.TGS.entities.masters.BookDressFees;
@Service
public class BookDressFeesInpl implements BookDressFeesService {
	@Autowired
	private BookDressFeesDao bookDressFeesDao;
	
	@Override
	public List<BookDressFees> getAllBookDressFees() {
		return bookDressFeesDao.findAll(Sort.by("academicYearCode"));
	}

	@Override
	public ResponseModel saveBookDressFees(BookDressFees bookDressFees) {
		List<BookDressFees> data = new ArrayList<BookDressFees>();
//		data.add(bookDressFeesDao.save(bookDressFees));
//		return data;
		ResponseModel res = new ResponseModel();
        if(bookDressFees.getId()>0) {
        	data.add(bookDressFeesDao.save(bookDressFees));
			res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
        }else {
			if(bookDressFeesDao.countByAcademicYearCodeAndStandard(bookDressFees.getAcademicYearCode(), bookDressFees.getStandard())>0) {
				res = new ResponseModel(Constants.DUPLICATE_RECORD, Constants.ERROR, true, null); 
			}else {
				data.add(bookDressFeesDao.save(bookDressFees));
				res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
			}
        }
		return res;
		
	}

	@Override
	public List<BookDressFees> findByAcademicYearCodeAndStandard(BookDressFees bookDressFees) {
		return bookDressFeesDao.findByAcademicYearCodeAndStandard(bookDressFees.getAcademicYearCode(), bookDressFees.getStandard());
	}
	
}
