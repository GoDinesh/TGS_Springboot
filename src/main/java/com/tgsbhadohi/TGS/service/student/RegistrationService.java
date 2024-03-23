package com.tgsbhadohi.TGS.service.student;

import java.util.List;
import java.util.Optional;

import com.tgsbhadohi.TGS.entities.student.Registration;

public interface RegistrationService {
	public List<Registration> getAllRegistration(Registration registration);
	public List<Registration> getRegistrationById(Long id);
	public List<Registration> saveRegistration(Registration registration);
	public List<Registration> search(Registration registration);
	public Integer getRollNumber(Registration registration);
	public Integer getRegistrationNumber();
	public List<Registration> filterListByKeyword(String keyword); 
	public boolean updateStatus(Registration studentList[]);
	public boolean updateFeesDetails(Registration registration);
}
