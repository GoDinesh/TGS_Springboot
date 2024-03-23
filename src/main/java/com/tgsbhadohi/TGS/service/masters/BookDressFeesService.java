package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.BookDressFees;

public interface BookDressFeesService {
	public List<BookDressFees> getAllBookDressFees();
	public List<BookDressFees> saveBookDressFees(BookDressFees bookDressFees);
	public List<BookDressFees>findByAcademicYearCodeAndStandard(BookDressFees bookDressFees);
//	public List<AcademicYear> getAllActiveAcademicYear(Boolean status);
}
