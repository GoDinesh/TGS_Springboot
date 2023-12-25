package com.tgsbhadohi.TGS.service.Fees;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.fees.StudentFeesInstallmentDao;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;

@Service
public class StudentFeesInstallmentImpl implements StudentFeesInstallmentService{

	private StudentFeesInstallmentDao studentFeesInstallmentDao;
	
	@Override
	public List<StudentFeesInstallment> getAllStudentFeesInstallment() {
		return studentFeesInstallmentDao.findAll(Sort.by("installmentDate"));
	}

	@Override
	public List<StudentFeesInstallment> saveStudentFeesInstallment(StudentFeesInstallment studentFeesInstallment) {
		List<StudentFeesInstallment> studentFeesInstallments = new ArrayList<>();
		studentFeesInstallments.add(studentFeesInstallmentDao.save(studentFeesInstallment));
		return studentFeesInstallments;
	}

//	@Override
//	public List<StudentFeesInstallment> search(StudentFeesInstallment studentFeesInstallment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
