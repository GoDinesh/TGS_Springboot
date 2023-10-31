package com.tgsbhadohi.TGS.service.Fees;

import java.util.List;

import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;

public interface StudentFeesInstallmentService {
	public List<StudentFeesInstallment> getAllStudentFeesInstallment();
	public List<StudentFeesInstallment> saveStudentFeesInstallment(StudentFeesInstallment studentFeesInstallment);
	//public List<StudentFeesInstallment> search(StudentFeesInstallment studentFeesInstallment);
}
