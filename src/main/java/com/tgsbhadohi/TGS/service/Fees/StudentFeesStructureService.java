package com.tgsbhadohi.TGS.service.Fees;

import java.util.List;

import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;

public interface StudentFeesStructureService {
	public List<StudentFeesStructure> getAllStudentFeesStructure();
	public List<StudentFeesStructure> saveStudentFeesStructure(StudentFeesStructure studentFeesStructure);
	//public List<StudentFeesStructure> search(StudentFeesStructure studentFeesStructure);
}
