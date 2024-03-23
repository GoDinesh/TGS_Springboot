package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.BookDressFees;

@Repository
public interface BookDressFeesDao extends JpaRepository<BookDressFees, Long>{
	public List<BookDressFees> findByAcademicYearCodeAndStandard(String academicYearCode, String standard);
	
}
