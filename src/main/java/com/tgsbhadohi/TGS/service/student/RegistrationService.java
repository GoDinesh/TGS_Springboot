package com.tgsbhadohi.TGS.service.student;

import java.util.List;
import java.util.Optional;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.student.Registration;

public interface RegistrationService {
	public List<Registration> getAllRegistration(Registration registration);
	public List<Registration> getRegistrationById(Long id);
	public ResponseModel saveRegistration(Registration registration);
	public ResponseModel savePromotedRegistration(Registration registration);
	public List<Registration> search(Registration registration);
	public List<Registration> dropoutList(Registration registration);
	public Integer getRollNumber(Registration registration);
	public Integer getRegistrationNumber();
	public List<Registration> filterListByKeyword(String keyword); 
	public boolean updateStatus(Registration studentList[]);
	public boolean updateFeesDetails(Registration registration);
	public boolean updateBookFeesDetails(Registration registration);	
	public boolean updateStudentDetails(Registration registration);
	public boolean dropout(Registration registration);
}
