package com.tgsbhadohi.TGS.service.student;

import com.tgsbhadohi.TGS.dao.student.RegistrationDao;
import com.tgsbhadohi.TGS.entities.student.Registration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RegistrationImpl implements RegistrationService {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  private RegistrationDao registrationDao;

  @Override
  public List<Registration> getAllRegistration(Registration registration) {
    return registrationDao.findAll(Sort.by("studentName"));
  }

  @Override
  public List<Registration> getRegistrationById(Long id) {
    List<Registration> data = new ArrayList<Registration>();
    data.add(registrationDao.findById(id).get());
    return data;
  }

//  @Transactional
  @Override
  public List<Registration> saveRegistration(Registration registration) {
    List<Registration> data = new ArrayList<Registration>();
    data.add(registrationDao.save(registration));
    return data;
  }

  @Override
  public List<Registration> search(Registration registration) {
    try {
      String query = "SELECT * from registration where";
      if (registration.getStandard().length() > 0) query =
        query + " standard='" + registration.getStandard() + "' and";
      if (registration.getAcademicYearCode().length() > 0) query =
        query +
        " academic_year_code='" +
        registration.getAcademicYearCode() +
        "' and";
      if (registration.getRegistrationNo().length() > 0) query =
        query +
        " registration_no='" +
        registration.getRegistrationNo() +
        "' and";
      if (registration.getFatherContactNo().length() > 0) query =
        query +
        " ( father_contact_no='" +
        registration.getFatherContactNo() +
        "' or mother_contact_number='" +
        registration.getFatherContactNo() +
        "' or emergency_number='" +
        registration.getFatherContactNo() +
        "' ) and";
      if (registration.getStudentName().length() > 0) query =
        query +
        " student_name like '%" +
        registration.getStudentName() +
        "%' and";

      query = query + " 1 order by student_name";
      Query qry = entityManager.createNativeQuery(query, Registration.class);

      List<Registration> studentList = qry.getResultList();
      return studentList;
    } catch (Exception e) {}

    return null;
  }

  @Override
  public Integer getRollNumber(Registration registration) {
    try {
      int rollnumber = registrationDao.getRollNumber(
        registration.getAcademicYearCode(),
        registration.getStandard()
      );
      return rollnumber;
    } catch (Exception e) {}
    return null;
  }

  @Override
  public List<Registration> filterListByKeyword(String keyword) {
    return registrationDao.filterListByKeyword(keyword);
  }

@Override
public boolean updateStatus(Registration[] studentList) {
	try {
		for (Registration registration : studentList) {
			registrationDao.updateIsActiveByRegistartionId(registration.getRegistrationId());
		}
	} catch (Exception e) {
		return false;
	}
	return true;
	
}
}
