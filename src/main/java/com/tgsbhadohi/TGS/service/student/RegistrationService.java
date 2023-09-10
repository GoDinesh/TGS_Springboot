package com.tgsbhadohi.TGS.service.student;

import java.util.List;

import com.tgsbhadohi.TGS.entities.student.Registration;

public interface RegistrationService {
	public List<Registration> getAllRegistration(Registration registration);
	public List<Registration> getRegistrationById(Long id);
	public List<Registration> saveRegistration(Registration registration);
	public List<Registration> search(Registration registration);
}
