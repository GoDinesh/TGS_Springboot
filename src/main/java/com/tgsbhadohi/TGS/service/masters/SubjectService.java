package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.Subject;

public interface SubjectService {
	public List<Subject> getAllSubject();
	public List<Subject> getSubjectById(Long id);
	public ResponseModel saveSubject(Subject subject);
	public List<Subject> getAllActiveSubject(Boolean status);
}
