package com.tgsbhadohi.TGS.service.Fees;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.fees.StudentFeesStructureDao;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;

@Service
public class StudentFeesStructureImpl implements StudentFeesStructureService {

	@Autowired
	private StudentFeesStructureDao studentFeesStructureDao;
	
	@Override
	public List<StudentFeesStructure> getAllStudentFeesStructure() {
		return studentFeesStructureDao.findAll();
	}

	@Override
	public List<StudentFeesStructure> saveStudentFeesStructure(StudentFeesStructure studentFeesStructure) {
		System.out.println();
		List<StudentFeesStructure> studentFeesStructuresList = new ArrayList<>();
		studentFeesStructuresList.add(studentFeesStructureDao.save(studentFeesStructure));
		return studentFeesStructuresList;		
	}

//	@Override
//	public List<StudentFeesStructure> search(StudentFeesStructure studentFeesStructure) {
//		return studentFeesStructureDao.findByRegistrationNoAndAcademicYearCodeAndClassCode(studentFeesStructure.getUserRegistrationNo().getRegistrationNo(),studentFeesStructure.getAcademicYearCode(), studentFeesStructure.getClassCode());
//	}

}
