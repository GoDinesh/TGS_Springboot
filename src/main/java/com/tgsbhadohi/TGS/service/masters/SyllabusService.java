package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Syllabus;

public interface SyllabusService {

    public ResponseModel save(Syllabus syllabus);

    List<Syllabus> saveAll(List<Syllabus> syllabusList);

    List<Syllabus> findAll();

    Syllabus findById(Long syllabusId);

    List<Syllabus> findByStandardAndAcademicYearCodeAndActiveTrue(
            String standard,
            String academicYearCode);

    void delete(Long syllabusId);
}