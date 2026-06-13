package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tgsbhadohi.TGS.entities.masters.TimeTable;


@Repository
public interface TimeTableDao extends JpaRepository<TimeTable, Long>{
	List<TimeTable> findByStandardAndAcademicYearCodeAndActiveTrue(
            String standard,
            String academicYearCode);

    long countByStandardAndAcademicYearCode(
            String standard,
            String academicYearCode);
}
