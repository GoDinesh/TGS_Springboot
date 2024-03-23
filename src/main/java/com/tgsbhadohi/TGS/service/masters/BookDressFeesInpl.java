package com.tgsbhadohi.TGS.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	public List<BookDressFees> saveBookDressFees(BookDressFees bookDressFees) {
		List<BookDressFees> data = new ArrayList<BookDressFees>();
		data.add(bookDressFeesDao.save(bookDressFees));
		return data;
	}

	@Override
	public List<BookDressFees> findByAcademicYearCodeAndStandard(BookDressFees bookDressFees) {
		return bookDressFeesDao.findByAcademicYearCodeAndStandard(bookDressFees.getAcademicYearCode(), bookDressFees.getStandard());
	}
	
}
